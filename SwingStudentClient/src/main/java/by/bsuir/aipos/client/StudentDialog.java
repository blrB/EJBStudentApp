package by.bsuir.aipos.client;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDialog {
    /**
     * Id of student to edit
     */
    private long id;
    /**
     * Name of dialog: edit or create
     */
    private String dialogName;
    /**
     * Labels names: FIRST NAME, LAST NAME, MIDDLE NAME, DATE OF BIRTH, ADDRESS, GROUP
     */
    private final static String FIRST_NAME = "FIRST NAME";
    private final static String LAST_NAME = "LAST NAME";
    private final static String MIDDLE_NAME = "MIDDLE NAME";
    private final static String DATE_OF_BIRTH = "DATE OF BIRTH";
    private final static String ADDRESS = "ADDRESS";
    private final static String GROUP = "GROUP";
    private String[] labelString = {LAST_NAME, FIRST_NAME, MIDDLE_NAME, ADDRESS};
    /**
     * Array of student groups for combo box
     */
    private String[] studentGroupArray;
    /**
     * Instance of created dialog
     */
    private JDialog dialog;
    /**
     * Connector between input and it's label
     */
    private Map<String, JTextField> fieldID = new HashMap<String, JTextField>();
    /**
     * Student group chooser
     */
    private JComboBox<String> group;
    /**
     * Date chooser for adding date of birth
     */
    private JDateChooser jDateChooser = new JDateChooser();
    /**
     * Instance of main window
     */
    private MainWindow mainWindow;

    /**
     * Creates dialog for editing/creating student
     *
     * @param mainWindow instance of main window
     * @param dialogName dialog title
     */
    public StudentDialog(MainWindow mainWindow, String dialogName) {
        this.mainWindow = mainWindow;
        this.dialogName = dialogName;
        this.studentGroupArray = getArrayGroupName();
        this.group = new JComboBox(studentGroupArray);
        for (int field = 0; field < labelString.length; field++) {
            JTextField jtfField = new JTextField(30);
            fieldID.put(labelString[field], jtfField);
        }
        jDateChooser.setDateFormatString("yyyy-MM-dd");
        jDateChooser.setDate(new Date());
        jDateChooser.getDateEditor().setEnabled(false);
    }

    /**
     * Shows dialog
     */
    public void show(){
        dialog = new JDialog(new JFrame(), dialogName, true);
        createFrame();
        dialog.setLocationRelativeTo(mainWindow.getFrame());
        dialog.setSize(500,200);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    /**
     * Close dialog
     */
    public void closeDialog(){
        dialog.dispose();
        dialog = null;
    }

    /**
     * Add content (form) to a dialog
     */
    private void createFrame(){
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        JLabel labelText = new JLabel(this.dialogName);
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID,labelText, 0, 0, 2, 1);
        for (int field = 0; field < labelString.length; field++) {
            labelText = new JLabel(labelString[field]);
            AddComponent.add(jPanelID, labelText, 0, field + 1, 1, 1);
            AddComponent.add(jPanelID, fieldID.get(labelString[field]), 1, field + 1, 1, 1);
        }
        int offset = labelString.length + 1;
        labelText = new JLabel(DATE_OF_BIRTH);
        AddComponent.add(jPanelID, labelText, 0, offset, 1, 1);
        AddComponent.add(jPanelID, jDateChooser, 1, offset, 1, 1);
        offset++;
        labelText = new JLabel(GROUP);
        AddComponent.add(jPanelID, labelText, 0, offset, 1, 1);
        AddComponent.add(jPanelID, group, 1, offset, 1, 1);
        dialog.add(jPanelID, BorderLayout.NORTH);
        JButton okButton = new JButton(dialogName);
        okButton.addActionListener(actionEvent -> checkAndSave());
        dialog.add(okButton, BorderLayout.SOUTH);
    }

    /**
     * Get array of all student group names
     *
     * @return array of all student group names
     */
    private String[] getArrayGroupName() {
        java.util.List<StudentGroup> studentGroupThrifts = mainWindow.getStudentClient().getStudentGroups();
        List<String> groupName = studentGroupThrifts.stream()
                .map(StudentGroup::getName).collect(Collectors.toList());
        return groupName.toArray(new String[groupName.size()]);
    }

    /**
     * Check if all required fields are entered. If at least one field is empty,
     * error message will be shown. If user entered all info, student will be saved
     */
    private void checkAndSave(){
        if (haveEmptyField()){
            JOptionPane.showMessageDialog(dialog,
                    "Some field are empty!",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            saveStudent();
        }
    }

    /**
     * Check required fields: first name, last name, address
     *
     * @return true if at least one empty field exists, false if all fields are valid
     */
    private boolean haveEmptyField() {
        return  getTextID(FIRST_NAME).isEmpty() ||
                getTextID(LAST_NAME).isEmpty() ||
                getTextID(ADDRESS).isEmpty();
    }

    /**
     * Save student in the student client
     */
    private void saveStudent(){
        StudentGroup group = mainWindow.getStudentClient()
                .getStudentGroup(this.group.getSelectedItem().toString());
        Student student = new Student(
                getTextID(FIRST_NAME),
                getTextID(LAST_NAME),
                getTextID(MIDDLE_NAME),
                jDateChooser.getDate(),
                getTextID(ADDRESS),
                group
        );
        student.setId(id);
        mainWindow.getStudentClient().saveStudent(student);
        closeDialog();
    }

    /**
     * Get content of JTextField of form by key
     *
     * @param key name of required field
     * @return content of JTextField
     */
    private String getTextID(String key) {
        return fieldID.get(key).getText();
    }

    /**
     * Set content to JTextField of form
     *
     * @param key name of field in witch value will be set
     * @param value new value of JTextField
     */
    private void setTextID(String key, String value) {
        fieldID.get(key).setText(value);
    }

    /**
     * Add selected student from table in dialog. Fill JTextFields by students
     * attributes (first name, last name, ext.)
     *
     * @param student xml model of student
     */
    public void setField(Student student){
        id = student.getId();
        setTextID(FIRST_NAME, student.getFirstName());
        setTextID(LAST_NAME, student.getLastName());
        if (student.getMiddleName() != null){
            setTextID(MIDDLE_NAME, student.getMiddleName());
        }
        jDateChooser.setDate(student.getDateOfBirth());
        setTextID(ADDRESS, student.getHomeAddress());
        group.setSelectedIndex
                (Arrays.asList(studentGroupArray).indexOf(student.getStudentGroup().getName()));
    }

}
