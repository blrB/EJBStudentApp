package by.bsuir.aipos.client;

import by.bsuir.aipos.PropertyUtils;
import by.bsuir.aipos.model.Student;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    /**
     * Logger
     */
    public static Logger logger = Logger.getLogger(StudentClient.class);
    /**
     * Main frame
     */
    private JFrame frame;
    /**
     * Student client
     */
    private StudentClient studentClient;
    /**
     * Table with students
     */
    private StudentTable studentTable;
    /**
     * Init property
     */
    private PropertyUtils propertyUtils = new PropertyUtils();

    /**
     * Create main window and add content to it
     */
    public MainWindow() {
        frame = new JFrame("REST Student Client");
        frame.setLayout(new BorderLayout());
        frame.add(createToolBar(), BorderLayout.NORTH);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new ExitAdapter());
        studentTable = new StudentTable(this);
        frame.add(studentTable, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Toolbar creator
     *
     * @return created toolbar
     */
    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(AddComponent.makeButton(new JButton(), "UPDATE.png", actionEvent -> updateTable()));
        toolBar.addSeparator();
        toolBar.add(AddComponent.makeButton(new JButton(), "ADD.png", actionEvent -> addStudent()));
        toolBar.add(AddComponent.makeButton(new JButton(), "EDIT.png", actionEvent -> editStudent()));
        toolBar.add(AddComponent.makeButton(new JButton(), "DELETE.png", actionEvent -> removeStudent()));
        return toolBar;
    }

    /**
     * Update table with students by getting data from server
     */
    public void updateTable() {
        logger.info("Update table");
        studentTable.updatePanel();
    }

    /**
     * Create new student and add it to a table
     */
    private void addStudent() {
        logger.info("Add new student");
        StudentDialog dialog = new StudentDialog(this, "Add new Student");
        dialog.show();
        updateTable();
    }

    /**
     * Edit selected student from a table
     */
    private void editStudent() {
        Student student = studentTable.getSelectedStudent();
        if (student != null) {
            logger.info("Edit student");
            StudentDialog dialog = new StudentDialog(this, "Edit Student");
            dialog.setField(student);
            dialog.show();
            updateTable();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Select student in table!",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Remove selected student from a table
     */
    private void removeStudent() {
        Student student = studentTable.getSelectedStudent();
        if (student != null) {
            logger.info("Remove student");
            int confirm = JOptionPane.showOptionDialog(
                    null, "Are You Sure to delete student " + student.getLastName() + "?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
                getStudentClient().deleteStudent(student.getId());
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Select student in table!",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Client app runner
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.runClient();
    }

    /**
     * Init client connection
     */
    private void runClient() {
        studentClient = new StudentClient(this);
        studentClient.start();
    }

    /**
     * Get student client
     *
     * @return student client instance
     */
    public StudentClient getStudentClient() {
        return studentClient;
    }

    /**
     * Get main window instance
     *
     * @return main window instance
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Return logger
     *
     * @return logger
     */
    public static Logger getLogger() {
        return logger;
    }
}

