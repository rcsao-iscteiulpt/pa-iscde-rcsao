package pa.iscde.docgeneration.test;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import pa.iscde.docGeneration.ext.DocGenServices;
import pt.iscte.pidesco.extensibility.PidescoView;

/**
 * View used for testing Doc Generator Component's Services and Extensions
 * 
 * @author Ricardo Silva
 *
 */
public class TestView implements PidescoView {

	private static TestView instance;
	private Text text;
	private DocGenServices services;

	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		instance = this;
		services= Activator.getInstance().getDocServices();
		
		viewArea.setLayout(new GridLayout(1, false));
		viewArea.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Services Button Composite
		Composite servicescomp = new Composite(viewArea, SWT.BORDER);
		GridData gridData = new GridData();
		servicescomp.setLayout(new GridLayout(10, false));
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = false;
		gridData.verticalAlignment = SWT.TOP;
		gridData.horizontalAlignment = GridData.FILL;
		
		//Button Creation
		Button getFilters = new Button(servicescomp, SWT.PUSH);
		getFilters.setText("get Filters ");
		
		Button getSelectedFile = new Button(servicescomp, SWT.PUSH);
		getSelectedFile.setText("Get Selected File ");
		
		Button filterWord = new Button(servicescomp, SWT.PUSH);
		filterWord.setText("Filter Word ");
		
		getFilters.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				inputText(services.getAllFilters().toString());
			}

		});
		
		getSelectedFile.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				inputText(services.getSelectedFile().getAbsolutePath());
			}

		});
		
		filterWord.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				services.filterWord("String");
			}

		});
		
		
		text = new Text(viewArea, SWT.MULTI | SWT.WRAP | SWT.BORDER);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		text.setLayoutData(gridData);
		
		text.setEditable(false);
	}

	public void inputText(String g) {
		text.append(g);
	}

	public static TestView getInstance() {
		return instance;
	}
}
