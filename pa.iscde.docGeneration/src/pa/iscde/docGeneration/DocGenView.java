package pa.iscde.docGeneration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.osgi.framework.ServiceReference;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;
import InfoClasses.Modifiers;
import pa.iscde.search.services.SearchService;
import pt.iscte.pidesco.extensibility.PidescoView;

/**
 * View of the Documentation Generation Component
 * @author Ricardo Silva
 *
 */
public class DocGenView implements PidescoView {

	private static DocGenView instance;

	private Map<MyCTabItem, File> openedfiles = new HashMap<MyCTabItem, File>();
	private CTabFolder folders;
	private EvaluateContributionsHandler extensions;
	private Set<String> activefilters = new HashSet<String>();
	private ArrayList<Button> filterchecks = new ArrayList<Button>();
	private String searchword;

	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		instance = this;
		extensions = new EvaluateContributionsHandler();

		// Component Icon Label
		Label label = new Label(viewArea, SWT.NONE);
		label.setImage(imageMap.get("icon.png"));

		// Getting Search Component reference
		ServiceReference<SearchService> refSearch = Activator.getInstance().getContext()
				.getServiceReference(SearchService.class);
		SearchService searchService = (SearchService) Activator.getInstance().getContext().getService(refSearch);
		searchService.addListener(new SearchListenersActions());

		viewArea.setLayout(new GridLayout(1, false));
		viewArea.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Filter Composite
		Composite filterscomp = new Composite(viewArea, SWT.BORDER);
		GridData gridData = new GridData();
		filterscomp.setLayout(new GridLayout(10, false));
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = false;
		gridData.verticalAlignment = SWT.TOP;
		gridData.horizontalAlignment = GridData.FILL;

		filterscomp.setLayoutData(gridData);

		// Filter Label
		Label labelfilters = new Label(filterscomp, SWT.FILL | SWT.BOLD);
		labelfilters.setText("Filters: ");
		FontData[] filterfD = labelfilters.getFont().getFontData();
		filterfD[0].setHeight(13);
		labelfilters.setFont(new Font(viewArea.getDisplay(), filterfD[0]));

		// Creating Filter Check buttons
		for (Modifiers s : Modifiers.values()) {
			Button button = new Button(filterscomp, SWT.CHECK);
			button.setText(s.toString());
			filterchecks.add(button);
		}

		// Creating Buttons
		Button cancelSearch = new Button(filterscomp, SWT.PUSH | SWT.RIGHT);
		cancelSearch.setText("Cancel SearchFilter  ");

		Button resetFilters = new Button(filterscomp, SWT.PUSH | SWT.RIGHT);
		resetFilters.setText("Reset All Filters ");

		// Adding Button Listeners
		resetFilters.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				ClearAllfilters();
			}

		});

		cancelSearch.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				removeFilter(searchword);
			}
		});

		addButtonCheckListeners(filterchecks);

		// CtabFolfers creation
		folders = new CTabFolder(viewArea, SWT.BORDER | SWT.V_SCROLL);
		folders.setLayout(new GridLayout());
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		folders.setLayoutData(gridData);
	}

	/**
	 * Method called for opening a new tab, it receives a file and read it's class
	 * information though ASTVisitor<br>
	 * Also checks if it's already opened, if it is then its return<br>
	 * If it's not then it proceeds to open a new tab displaying the class
	 * information of the file
	 * 
	 * @param file
	 */
	public void openfile(File file) {
		ClassInfoChecker c = new ClassInfoChecker();
		Activator.getInstance().getEditorservice().parseFile(file, c);

		for (MyCTabItem item : openedfiles.keySet()) {
			if (item.name.equalsIgnoreCase((String) c.getClassbasicinfo().get("ClassName"))) {
				folders.setSelection(item);
				return;
			}
		}

		MyCTabItem newtab = new MyCTabItem(folders, SWT.CLOSE | SWT.V_SCROLL, c);

		folders.setSelection(newtab);

		openedfiles.put(newtab, file);

		// System.out.println(Activator.getInstance().getTaskservice().getTasks());

	}

	/**
	 * Method used for updating a specific file, usually called whenever a save
	 * occurs
	 * 
	 * @param c info of the updated file
	 * @param f file that was saved in the JavaEditor
	 */
	public void updateFile(ClassInfoChecker c, File f) {
		for (MyCTabItem item : openedfiles.keySet()) {
			if (openedfiles.get(item).getName().equals(f.getName()))
				item.drawTables(c);
		}
	}

	/**
	 * Method that updates all opened tabs in the Documentation View
	 */
	public void refresh() {
		for (MyCTabItem item : openedfiles.keySet()) {
			item.drawTables(item.c);
		}
	}

	/**
	 * Used specifically in the interaction with the Search Component Similar to
	 * addFilter(), this method also adds a new filter but it removes the old
	 * searchWord value from the list and adds the new one
	 * 
	 * @param searchWord string new searchvalue to replaced the old value
	 */
	public void SearchWord(String searchWord) {
		if (!searchWord.trim().equals("")) {
			removeFilter(this.searchword);
			this.searchword = searchWord;
			addFilter(searchWord);
		}
	}

	public void addButtonCheckListeners(ArrayList<Button> filterchecks) {
		for (Button button : filterchecks) {
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (button.getSelection()) {
						addFilter(button.getText());
					} else {
						removeFilter(button.getText());
					}

				}
			});
		}

	}

	/**
	 * Adds a new filter and refreshes the tables
	 * @param g String new word to be added to the filter
	 */
	public void addFilter(String g) {
		activefilters.add(g);
		refresh();
	}

	/**
	 * removes a filter and refreshes the tables
	 * @param g String word to be removed of the filter
	 */
	public void removeFilter(String g) {
		for (String s : activefilters) {
			if (s.equals(g)) {
				activefilters.remove(s);
				break;
			}
		}
		refresh();
	}

	public void ClearAllfilters() {
		activefilters.clear();
		for (Button b : filterchecks)
			b.setSelection(false);
		refresh();
	}

	/**
	 * Class used for storing additional information about the tables and the tab(class) name
	 * @author Ricardo Silva
	 *
	 */
	private class MyCTabItem extends CTabItem {

		private String name;
		private Table fields;
		private Table constructors;
		private Table methods;
		private ClassInfoChecker c;

		public MyCTabItem(CTabFolder parent, int style, ClassInfoChecker c) {
			super(parent, style);
			this.name = c.getClassbasicinfo().get("ClassName").toString();
			this.c = c;

			this.addDisposeListener(new DisposeListener() {

				@Override
				public void widgetDisposed(DisposeEvent e) {
					for (MyCTabItem item : openedfiles.keySet()) {
						if (c.getClassbasicinfo().get("ClassName").equals(item.name)) {
							openedfiles.remove(item);
							break;
						}
					}
				}
			});

			drawTables(c);

		}

		private void drawTables(ClassInfoChecker c) {
			Boolean filter = !activefilters.isEmpty();
			DocFilter dfilter = new DocFilter(activefilters);

			Group group = new Group(folders, SWT.V_SCROLL);
			group.setLayout(new RowLayout(SWT.VERTICAL));

			// Class Label
			Label classlabel = new Label(group, SWT.FILL);
			classlabel.setText("Class " + c.getClassbasicinfo().get("ClassName"));
			FontData[] classfD = classlabel.getFont().getFontData();
			classfD[0].setHeight(18);
			classlabel.setFont(new Font(folders.getDisplay(), classfD[0]));

			// Modifier Label
			Label modlabel = new Label(group, SWT.FILL);
			modlabel.setText("Modifiers: " + c.getClassbasicinfo().get("Modifiers"));
			FontData[] modfD = modlabel.getFont().getFontData();
			modfD[0].setHeight(18);
			modlabel.setFont(new Font(folders.getDisplay(), modfD[0]));

			// Fields Label
			Label fieldlabel = new Label(group, SWT.FILL);
			fieldlabel.setText("Fields");
			FontData[] fieldfD = fieldlabel.getFont().getFontData();
			fieldfD[0].setHeight(16);
			fieldlabel.setFont(new Font(folders.getDisplay(), fieldfD[0]));

			// Fields Table
			fields = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
			fields.setLinesVisible(true);
			fields.setHeaderVisible(true);

			

			TableColumn fieldtc1 = new TableColumn(fields, SWT.CENTER | SWT.BORDER);
			TableColumn fieldtc2 = new TableColumn(fields, SWT.CENTER | SWT.BORDER);

			fieldtc1.setText("Field and Type");
			fieldtc2.setText("Modifiers");
			fieldtc1.setWidth(250);
			fieldtc2.setWidth(250);

			
			//Filter Fields
			for (FieldInfo f : c.getClassfields()) {
				if (!filter || dfilter.accept(f)) {
					TableItem newitem = new TableItem(fields, SWT.NONE);
					newitem.setText(f.getFieldInfo());
				}
			}

			// Constructors Label
			Label constructorslabel = new Label(group, SWT.FILL);
			constructorslabel.setText("Constructors");
			FontData[] constructorfD = constructorslabel.getFont().getFontData();
			constructorfD[0].setHeight(16);
			constructorslabel.setFont(new Font(folders.getDisplay(), constructorfD[0]));

			// Constructors Table
			constructors = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
			constructors.setLinesVisible(true);
			constructors.setHeaderVisible(true);

			TableColumn constructortc1 = new TableColumn(constructors, SWT.CENTER | SWT.BORDER);
			TableColumn constructortc2 = new TableColumn(constructors, SWT.CENTER | SWT.BORDER);

			constructortc1.setText("Constructor");
			constructortc2.setText("Modifiers");
			constructortc1.setWidth(250);
			constructortc2.setWidth(250);

			//Filter Constructors
			for (ConstructorInfo m : c.getClassconstructors()) {
				if (!filter || dfilter.accept(m)) {

					TableItem newitem = new TableItem(constructors, SWT.NONE);
					newitem.setText(m.getConstructorInfo());
				}
			}

			

			// Methods Label
			Label methodslabel = new Label(group, SWT.FILL);
			methodslabel.setText("Methods");
			FontData[] methodfD = methodslabel.getFont().getFontData();
			methodfD[0].setHeight(16);
			methodslabel.setFont(new Font(folders.getDisplay(), methodfD[0]));

			// Methods Table
			methods = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
			methods.setLinesVisible(true);
			methods.setHeaderVisible(true);

			TableColumn tc1 = new TableColumn(methods, SWT.CENTER | SWT.BORDER);
			TableColumn tc2 = new TableColumn(methods, SWT.CENTER | SWT.BORDER);
			TableColumn tc3 = new TableColumn(methods, SWT.CENTER | SWT.BORDER);
			tc1.setText("Method");
			tc2.setText("Modifiers");
			tc3.setText("Return Type");
			tc1.setWidth(250);
			tc2.setWidth(250);
			tc3.setWidth(250);

			//Filter Methods
			for (MethodInfo m : c.getClassmethods()) {
				if (!filter || dfilter.accept(m)) {
					TableItem newitem = new TableItem(methods, SWT.NONE);
					newitem.setText(m.getMethodInfo());
				}
			}

			//Add Listeners
			addClickListener(fields, 'F');
			addClickListener(constructors, 'C');
			addClickListener(methods, 'M');

			this.setControl(group);

			//Set tab name
			this.setText(name);

		}

		/**
		 * Method for adding a DoubleClick Listener to tables 
		 * @param t Table in which the listener will be added
		 * @param type char type of table clicked, e.g ('C' = Constructor, 'M' = Method, 'F' = Field)
		 */
		private void addClickListener(Table t, char type) {
			Listener l = new Listener() {
				public void handleEvent(Event e) {
					TableItem[] selection = t.getSelection();
					ArrayList<String> info = new ArrayList<String>();

					for (int i = 0; !selection[0].getText(i).equals(""); i++) {
						info.add(selection[0].getText(i));
					}

					extensions.doubleClick(info, type);

					String word = selection[0].toString().replaceAll("(.*)[\\{]|[\\}](.*)", "");
					FileScanner scanner = new FileScanner();
					File selectedfile = openedfiles.get(folders.getSelection());
					int offset = scanner.ScanforWord(selectedfile, word);
					scanner.Select(selectedfile, offset, word.length());
				}
			};
			t.addListener(SWT.DefaultSelection, l);
		}

	}

	public static DocGenView getInstance() {
		return instance;
	}

	public File getSelectedFile() {
		return openedfiles.get(folders.getSelection());
	}

	public Set<String> getActivefilters() {
		return activefilters;
	}

}
