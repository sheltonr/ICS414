import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * iCalendarGUI for Team Cocoa's ICS 314 Project.
 * 
 * Functions
 * -iCalendarGUI()						|	iCalendarGUI constructor.
 * -initialize()						|	Initializes the fields of the GUI.
 * -focusGained(FocusEvent event)		|	Performs actions when focus is gained.
 * -focusLost(FocusEvent event)			|	Performs actions when focus field is lost.
 * -highlight(Color color, String type)	|	Highlights field with given color.
 * -actionPerformed(ActionEvent event)	|	Responds to actions of user.
 * -generate()							|	Generates the GUI for the .ics FileMaker.
 *
 * 8/10/2013
 */

public class iCalendarGUI extends javax.swing.JPanel implements ActionListener,
		FocusListener {

	/* all the buttons, fields, labels */
	private final JLabel statusMessage = new JLabel();
	private final JTextField descriptionField = new JTextField();
	private final JTextField locationField = new JTextField();
	private final JTextField eventField = new JTextField();
	private final JTextField commentField = new JTextField();
	private final JButton generateButton = new JButton();
	private final JButton clearAllButton = new JButton();
	private final JLabel eventLabel = new JLabel();
	private final JLabel descriptionLabel = new JLabel();;
	private final JLabel endTimeLabel = new JLabel();
	private final JLabel locationLabel = new JLabel();
	private final JLabel startTimeLabel = new JLabel();
	private final JLabel startTimeDateLabel = new JLabel();
	private final JLabel endTimeDateLabel = new JLabel();
	private final JLabel startTimeTimeLabel = new JLabel();
	private final JLabel endTimeTimeLabel = new JLabel();
	private final JLabel recurringLabel = new JLabel();
	private final JLabel repeatLabel = new JLabel();
	private final JLabel untilLabel = new JLabel();
	private final JLabel exceptionDatesLabel = new JLabel();
    private final JLabel commentLabel = new JLabel();
    private final JLabel classLabel = new JLabel();
	private final JFormattedTextField startTimeDateField = new JFormattedTextField();
	private final JFormattedTextField endTimeDateField = new JFormattedTextField();
	private final JFormattedTextField startTimeTimeField = new JFormattedTextField();
	private final JFormattedTextField endTimeTimeField = new JFormattedTextField();
	private final JFormattedTextField exceptionDatesField = new JFormattedTextField();
	private final JFormattedTextField untilField = new JFormattedTextField();
	private final JCheckBox recurringCheckBox = new JCheckBox("", false);
	private JComboBox<String> repeatDropDown = new JComboBox<String>();
    private JComboBox<String> classDropDown = new JComboBox<String>();

	/**
	 * iCalendarGUI constructor.
	 */
	public iCalendarGUI() {
		initialize();
	}
	
	/**
	 * Initializes the fields of the GUI.
	 * @return True - Successful initialization of GUI.
	 * 		   False - Failed initialization of GUI.
	 */
	private boolean initialize() {
		try {
			/* add the labels, fields, buttons */
			add(statusMessage);
			add(descriptionField);
			add(locationField);
			add(eventField);
			add(generateButton);
			add(clearAllButton);
			add(eventLabel);
			add(descriptionLabel);
			add(endTimeLabel);
			add(locationLabel);
			add(startTimeLabel);
			add(startTimeDateLabel);
			add(endTimeDateLabel);
			add(startTimeTimeLabel);
			add(endTimeTimeLabel);
			add(startTimeDateField);
			add(endTimeDateField);
			add(startTimeTimeField);
			add(endTimeTimeField);
			add(recurringCheckBox);
			add(recurringLabel);
			add(repeatLabel);
			add(untilLabel);
			add(untilField);
			add(repeatDropDown);
			add(exceptionDatesField);
			add(exceptionDatesLabel);
            add(commentLabel);
            add(classLabel);
            add(classDropDown);
            add(commentField);

			/* set initial text */
			statusMessage.setFont(new java.awt.Font("Verdana", 1, 12));
			statusMessage.setText("Welcome to team Cocoa's .ICS generator!");
			eventLabel.setText("Event");
			locationLabel.setText("Location");
			descriptionLabel.setText("Description");
			descriptionField.setText("Describe the event");
			commentLabel.setText("Comment");
			commentField.setText("Insert comment");
			classLabel.setText("Classification");
			eventField.setText("What's happening?");
			locationField.setText("Where at?");
			startTimeLabel.setText("Start Time");
			endTimeLabel.setText("End Time");
			startTimeDateLabel.setText("Date");
			endTimeDateLabel.setText("Date");
			startTimeTimeLabel.setText("Time");
			endTimeTimeLabel.setText("Time");
			startTimeDateField.setText("MM/DD/YYYY");
			endTimeDateField.setText("MM/DD/YYYY");
			startTimeTimeField.setText("HH:MM:SS");
			endTimeTimeField.setText("HH:MM:SS");
			untilField.setText("MM/DD/YYYY");
			recurringLabel.setText("Will this be a recurring event?");
			repeatLabel.setText("Repeat");
			untilLabel.setText("Until");
			generateButton.setText("Generate!");
			clearAllButton.setText("Clear All");
			repeatDropDown.addItem("Daily");
			repeatDropDown.addItem("Weekly");
			repeatDropDown.addItem("Monthly");
			repeatDropDown.addItem("Yearly");
            classDropDown.addItem("Public");
			classDropDown.addItem("Private");
			classDropDown.addItem("Confidential");
			exceptionDatesLabel.setText("Exception Dates");
			exceptionDatesField.setText("MM/DD/YYYY");

			/* listeners */
			eventField.addActionListener(this);
			locationField.addActionListener(this);
			descriptionField.addActionListener(this);
			commentField.addActionListener(this);
			clearAllButton.addActionListener(this);
			generateButton.addActionListener(this);
			untilField.addActionListener(this);
			recurringCheckBox.addActionListener(this);
			exceptionDatesField.addActionListener(this);
			descriptionField.addFocusListener(this);
			commentField.addFocusListener(this);
			locationField.addFocusListener(this);
			eventField.addFocusListener(this);
			startTimeDateField.addFocusListener(this);
			endTimeDateField.addFocusListener(this);
			startTimeTimeField.addFocusListener(this);
			endTimeTimeField.addFocusListener(this);
			untilField.addFocusListener(this);
			exceptionDatesField.addFocusListener(this);
                        
			/* layout for components */
			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startTimeDateLabel)
                    .addComponent(startTimeTimeLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(generateButton)
                        .addGap(29, 29, 29)
                        .addComponent(clearAllButton))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startTimeLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(startTimeDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(72, 72, 72)
                                        .addComponent(endTimeDateLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(recurringLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(recurringCheckBox))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(repeatLabel)
                                            .addComponent(untilLabel)
                                            .addComponent(exceptionDatesLabel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                
                                         //after the check box label
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(repeatDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(untilField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(exceptionDatesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(startTimeTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addComponent(endTimeTimeLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endTimeLabel)
                                    .addComponent(endTimeDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(endTimeTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eventLabel)
                                    .addComponent(locationLabel)
                                    .addComponent(descriptionLabel)
                                    .addComponent(commentLabel)
                                    .addComponent(classLabel))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eventField, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                    .addComponent(locationField)
                                    .addComponent(descriptionField)
                                    .addComponent(commentField)
                                    .addComponent(classDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(statusMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        //vertical gaps
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(statusMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventLabel)
                    .addComponent(eventField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationLabel)
                    .addComponent(locationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(commentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commentLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classLabel)
                    .addComponent(classDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                
                /*gap in between upper info and start time info*/
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTimeLabel)
                    .addComponent(endTimeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTimeDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endTimeDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startTimeDateLabel)
                    .addComponent(endTimeDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTimeTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endTimeTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startTimeTimeLabel)
                    .addComponent(endTimeTimeLabel))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recurringLabel)
                    .addComponent(recurringCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(repeatLabel)
                    .addComponent(repeatDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(untilLabel)
                    .addComponent(untilField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)//, 15, Short.MAX_VALUE)
                
                //exceptions group right before buttons
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exceptionDatesLabel)
                    .addComponent(exceptionDatesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                
                //buttons group
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateButton)
                    .addComponent(clearAllButton))
                .addContainerGap())
        );
            if (recurringCheckBox.isSelected() == true) {
				untilField.setEnabled(true);
				repeatDropDown.setEnabled(true);
				exceptionDatesField.setEnabled(true);
                                classDropDown.setEnabled(true);
			} else {
				untilField.setEnabled(false);
				repeatDropDown.setEnabled(false);
				exceptionDatesField.setEnabled(false);
				classDropDown.setEnabled(true);
			}
            }catch(Exception e){
                return false;
            }
            return true;
	}
	
	/**
	 * Performs actions when focus is gained.
	 */
	public void focusGained(FocusEvent event) {
		if (event.getSource() == descriptionField) {
			descriptionField.setText("");
		}
		if(event.getSource() == commentField) {
			commentField.setText("");
		}
		if (event.getSource() == locationField) {
			locationField.setText("");
		}
		if (event.getSource() == eventField) {
			eventField.setText("");
		}
		if (event.getSource() == startTimeDateField) {
			if (startTimeDateField.getText().equals("MM/DD/YYYY"))
				startTimeDateField.setText("");
		}
		if (event.getSource() == endTimeDateField) {
			if (endTimeDateField.getText().equals("MM/DD/YYYY"))
				endTimeDateField.setText("");
		}
		if (event.getSource() == startTimeTimeField) {
			if (startTimeTimeField.getText().equals("HH:MM:SS"))
				startTimeTimeField.setText("");
		}
		if (event.getSource() == endTimeTimeField) {
			if (endTimeTimeField.getText().equals("HH:MM:SS"))
				endTimeTimeField.setText("");
		}
		if (event.getSource() == untilField) {
			if (untilField.getText().equals("MM/DD/YYYY"))
				untilField.setText("");
		}
		if (event.getSource() == exceptionDatesField) {
			if (exceptionDatesField.getText().equals("MM/DD/YYYY"))
				exceptionDatesField.setText("");
		}
	}
	
	/**
	 * Performs actions when focus field is lost.
	 */
	public void focusLost(FocusEvent event) {
		if (event.getSource() == startTimeDateField) {
			Color status = (ICSFormat.valid(startTimeDateField.getText(),"00:00:00").equals(""))? 
					Color.YELLOW: 
					Color.GREEN;
			startTimeDateField.setBackground(status);
		}
		if (event.getSource() == endTimeDateField) {
			Color status = (ICSFormat.valid(endTimeDateField.getText(),"00:00:00").equals(""))? 
					Color.YELLOW: 
					Color.GREEN;
			endTimeDateField.setBackground(status);
		}
		if (event.getSource() == startTimeTimeField) {
			Color status = (ICSFormat.valid("1/1/0001",startTimeTimeField.getText()).equals(""))? 
					Color.YELLOW: 
					Color.GREEN;
			startTimeTimeField.setBackground(status);
		}
		if (event.getSource() == endTimeTimeField) {
			Color status = (ICSFormat.valid("1/1/0001",endTimeTimeField.getText()).equals(""))? 
					Color.YELLOW: 
					Color.GREEN;
			endTimeTimeField.setBackground(status);
		}
		if (event.getSource() == exceptionDatesField) {
			Color status = (ICSFormat.valid(exceptionDatesField.getText(),"00:00").equals(""))? 
					Color.YELLOW: 
					Color.GREEN;
			exceptionDatesField.setBackground(status);
		}
		if (event.getSource() == untilField) {
			Color status = (ICSFormat.valid(untilField.getText(),"00:00").equals(""))? 
					Color.YELLOW: 
					Color.GREEN;
			untilField.setBackground(status);
		}
	}
	
	/**
	 * Highlights field with given color.
	 * @param color - color to be used 
	 * @param type - date or time
	 */
	public void highlight(Color color, String type) {
		if (type.equals("date")) {
			startTimeDateField.setBackground(color);
			endTimeDateField.setBackground(color);
		} else if (type.equals("time")) {
			startTimeTimeField.setBackground(color);
			endTimeTimeField.setBackground(color);
		}
	}

	/**
	 * Responds to actions of user.
	 */
	public void actionPerformed(ActionEvent event) {
		try {
			//recurring event actions.
			if (recurringCheckBox.isSelected() == true) {
				untilField.setEnabled(true);
				repeatDropDown.setEnabled(true);
				exceptionDatesField.setEnabled(true);
			} else {
				untilField.setEnabled(false);
				repeatDropDown.setEnabled(false);
				exceptionDatesField.setEnabled(false);
			}
			
			// Clears all fields (convenient)
			if (event.getSource() == clearAllButton) {
				statusMessage.setText("All fields cleared!!");
				eventField.setText("");
				commentField.setText("");
				locationField.setText("");
				descriptionField.setText("");
				startTimeDateField.setText("");
				endTimeDateField.setText("");
				startTimeTimeField.setText("");
				endTimeTimeField.setText("");
				startTimeTimeField.setBackground(Color.WHITE);
				endTimeTimeField.setBackground(Color.WHITE);
				startTimeDateField.setBackground(Color.WHITE);
				endTimeDateField.setBackground(Color.WHITE);
			}

			if (event.getSource() == generateButton) {

				// check that the times make contexual sense
				String beginTime=ICSFormat.valid(startTimeDateField.getText(),startTimeTimeField.getText());
				String endTime=ICSFormat.valid(endTimeDateField.getText(),endTimeTimeField.getText());
				int state=ICSFormat.compare(beginTime,endTime);
				if(state==1){		//if all is good
					highlight(Color.GREEN, "date");
					highlight(Color.GREEN, "time");
				}else if(state==-2){	//if date was bad
					highlight(Color.YELLOW, "date");
					highlight(Color.GREEN, "time");
					statusMessage.setText("Check your start and end dates");
					return;
				}else if(state==-3){	//if time was bad
					highlight(Color.GREEN, "date");
					highlight(Color.YELLOW, "time");
					statusMessage.setText("Check your start and end times");
					return;
				}else{			//error
					statusMessage.setText("failed");
					return;
				}
			
				TimeZone tz = Calendar.getInstance().getTimeZone();
				FileMaker generator = new FileMaker();
				generator.set_attribute("VERSION", "2.0", "calendar");
				generator.set_attribute("CALSCALE", "GREGORIAN", "calendar");
				generator.set_attribute("SUMMARY", eventField.getText());
				generator.set_attribute("CLASS", classDropDown.getSelectedItem().toString().toUpperCase());
				generator.set_attribute("DESCRIPTION",descriptionField.getText());
				generator.set_attribute("COMMENT", commentField.getText());
				generator.set_attribute("DTSTAMP;TZID=" + tz.getID(),ICSFormat.timestamp());
				generator.set_attribute("DTSTART;TZID=" + tz.getID(),ICSFormat.valid(startTimeDateField.getText(),startTimeTimeField.getText()));
				generator.set_attribute("DTEND;TZID=" + tz.getID(),ICSFormat.valid(endTimeDateField.getText(),endTimeTimeField.getText()));
				if(recurringCheckBox.isSelected() == true){
					String rruleAttributes = "FREQ=" + repeatDropDown.getSelectedItem().toString().toUpperCase();
					String rruleExceptions="";
					if(!ICSFormat.valid(untilField.getText()).equals("") && !untilField.getText().equals("MM/DD/YYYY")) {
						rruleAttributes += ";UNTIL="+ICSFormat.valid(untilField.getText(), "00:00");
					}
					generator.set_attribute("RRULE",rruleAttributes);
					if(!exceptionDatesField.getText().equals("") && !exceptionDatesField.getText().equals("MM/DD/YYYY")) {
						String[] str = exceptionDatesField.getText().split(",");
						for(int i = 0; i < str.length; i++) {
							rruleExceptions += ICSFormat.valid(str[i],startTimeTimeField.getText());
							rruleExceptions += (i==str.length - 1)?"":",";
						}
						generator.set_attribute("EXDATE;TZID=" + tz.getID(), rruleExceptions);
					}
				}
				generator.set_attribute("LOCATION", locationField.getText());
				if (generator.generate(eventField.getText().replaceAll(" ", "_"))) {
					statusMessage.setText(".ICS file generated! Check it out.");
				} else {
					statusMessage.setText("Bad Input");
				}
			}
		} catch (Exception E) {
			statusMessage.setText("Something went wrong.");
		}
	}

	/**
	 * Generates the GUI for the .ics FileMaker.
	 * @return True - Successfully generated GUI.
	 * 		   False - Failed generation of GUI.
	 */
	 public boolean generate() {
		try {
			JFrame window = new JFrame();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setTitle("t3@m C0cO@ LOL");
			iCalendarGUI panel = new iCalendarGUI();
			window.add(panel);
			window.pack();
			window.setVisible(true);
			panel.setLayout(new BorderLayout());
			
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
}