package pa.iscde.docGeneration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;
import pt.iscte.pidesco.extensibility.PidescoView;

public class DocGenView implements PidescoView {

	private static DocGenView instance;
	
	private Map<String, File> openedfiles = new HashMap<String, File>();
	private CTabFolder folders;

	public DocGenView() {
		instance = this;
	}

	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		viewArea.setLayout(new FillLayout(SWT.VERTICAL | SWT.V_SCROLL));
		folders = new CTabFolder(viewArea, SWT.BORDER | SWT.V_SCROLL);
	}

	public void openfile(ClassInfoChecker c, File file) {
		if (openedfiles.containsKey(c.getClassbasicinfo().get("ClassName"))) {
			for (CTabItem item : folders.getItems()) {
				if (c.getClassbasicinfo().get("ClassName").equals(item.getText())) {
					folders.setSelection(item);
				}
			}
			return;
		}

		openedfiles.put((String) c.getClassbasicinfo().get("ClassName"), file);

		CTabItem newtab = new CTabItem(folders, SWT.CLOSE | SWT.FILL | SWT.V_SCROLL);

		newtab.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {

				for (String name : openedfiles.keySet()) {
					if (c.getClassbasicinfo().get("ClassName").equals(name)) {
						openedfiles.remove(name);
					}
				}

			}
		});

		Group group = new Group(folders, SWT.V_SCROLL);
		group.setLayout(new RowLayout(SWT.VERTICAL));

		// Class
		Label classlabel = new Label(group, SWT.FILL);
		classlabel.setText("Class " + c.getClassbasicinfo().get("ClassName"));
		FontData[] classfD = classlabel.getFont().getFontData();
		classfD[0].setHeight(18);
		classlabel.setFont(new Font(folders.getDisplay(), classfD[0]));

		Label modlabel = new Label(group, SWT.FILL);
		modlabel.setText("Modifiers: " + c.getClassbasicinfo().get("Modifiers"));
		FontData[] modfD = modlabel.getFont().getFontData();
		modfD[0].setHeight(18);
		modlabel.setFont(new Font(folders.getDisplay(), modfD[0]));

		// Fields
		Label fieldlabel = new Label(group, SWT.FILL);
		fieldlabel.setText("Fields");
		FontData[] fieldfD = fieldlabel.getFont().getFontData();
		fieldfD[0].setHeight(16);
		fieldlabel.setFont(new Font(folders.getDisplay(), fieldfD[0]));

		Table fields = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		fields.setLinesVisible(true);
		fields.setHeaderVisible(true);

		addClickListener(fields);

		TableColumn fieldtc1 = new TableColumn(fields, SWT.CENTER | SWT.BORDER);
		TableColumn fieldtc2 = new TableColumn(fields, SWT.CENTER | SWT.BORDER);

		fieldtc1.setText("Field and Type");
		fieldtc2.setText("Modifiers");
		fieldtc1.setWidth(400);
		fieldtc2.setWidth(400);

		for (FieldInfo f : c.getClassfields()) {
			TableItem newitem = new TableItem(fields, SWT.NONE);
			newitem.setText(f.getFieldInfo());
		}

		// Constructors

		Label constructorslabel = new Label(group, SWT.FILL);
		constructorslabel.setText("Constructors");
		FontData[] constructorfD = constructorslabel.getFont().getFontData();
		constructorfD[0].setHeight(16);
		constructorslabel.setFont(new Font(folders.getDisplay(), constructorfD[0]));

		Table constructors = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		constructors.setLinesVisible(true);
		constructors.setHeaderVisible(true);

		TableColumn constructortc1 = new TableColumn(constructors, SWT.CENTER | SWT.BORDER);
		TableColumn constructortc2 = new TableColumn(constructors, SWT.CENTER | SWT.BORDER);

		constructortc1.setText("Constructor");
		constructortc2.setText("Modifiers");
		constructortc1.setWidth(400);
		constructortc2.setWidth(400);

		for (ConstructorInfo m : c.getClassconstructors()) {
			TableItem newitem = new TableItem(constructors, SWT.NONE);
			newitem.setText(m.getConstructorInfo());
		}

		addClickListener(constructors);

		// Methods
		Label methodslabel = new Label(group, SWT.FILL);
		methodslabel.setText("Methods");
		FontData[] methodfD = methodslabel.getFont().getFontData();
		methodfD[0].setHeight(16);
		methodslabel.setFont(new Font(folders.getDisplay(), methodfD[0]));

		Table methods = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		methods.setLinesVisible(true);
		methods.setHeaderVisible(true);

		TableColumn tc1 = new TableColumn(methods, SWT.CENTER | SWT.BORDER);
		TableColumn tc2 = new TableColumn(methods, SWT.CENTER | SWT.BORDER);
		TableColumn tc3 = new TableColumn(methods, SWT.CENTER | SWT.BORDER);
		tc1.setText("Method");
		tc2.setText("Modifiers");
		tc3.setText("Return Type");
		tc1.setWidth(400);
		tc2.setWidth(400);
		tc3.setWidth(200);

		for (MethodInfo m : c.getClassmethods()) {
			TableItem newitem = new TableItem(methods, SWT.NONE);
			newitem.setText(m.getMethodInfo());
		}

		addClickListener(methods);

		newtab.setControl(group);

		// Text text = new Text(newtab, SWT.NONE);

		newtab.setText(c.getClassbasicinfo().get("ClassName").toString());

		folders.setSelection(newtab);

	}

	private void addClickListener(Table t) {
		Listener l = new Listener() {
			public void handleEvent(Event e) {
				TableItem[] selection = t.getSelection();
				String word = selection[0].toString().replaceAll("(.*)[\\{]|[\\}](.*)", "");
				FileScanner scanner = new FileScanner();

				File selectedfile = openedfiles.get(folders.getSelection().getText());
				int offset = scanner.ScanforWord(selectedfile, word);
				scanner.Select(selectedfile, offset, word.length());
			}
		};
		t.addListener(SWT.DefaultSelection, l);
	}

	public void reUpdateClass(ClassInfoChecker c, File f) {
		for (CTabItem item : folders.getItems()) {
			if (c.getClassbasicinfo().get("ClassName").equals(item.getText())) {
				item.dispose();
			}
		}
		
		openfile(c,f);
	}

	public static DocGenView getInstance() {
		return instance;
	}

}
