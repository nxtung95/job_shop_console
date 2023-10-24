package jop_shop;

import jop_shop.dao.CustomerDao;
import jop_shop.dao.DepartmentDao;
import jop_shop.dao.ProcessDao;
import jop_shop.model.CutProcess;
import jop_shop.model.FitProcess;
import jop_shop.model.PaintProcess;
import jop_shop.model.Process;

import java.util.Scanner;

public class Main {
    private static CustomerDao customerDao = new CustomerDao();
    private static DepartmentDao departmentDao = new DepartmentDao();
    private static ProcessDao processDao = new ProcessDao();

    public static void main(String[] args) {
        Scanner keyboard = null;
        try {
            keyboard = new Scanner(System.in);
            while (true) {
                System.out.println("WELCOME TO THE JOB-SHOP ACCOUNTING DATABASE SYSTEM");
                System.out.println("1. Enter a new customer (30/day).");
                System.out.println("2. Enter a new department (infrequent).");
                System.out.println("3. Enter a new process-id and its department together with its type and information relevant to the type (infrequent).");
                System.out.println("4. Enter a new assembly with its customer-name, assembly-details, assembly-id, and date ordered and associate it with one or more processes (40/day).");
                System.out.println("5. Create a new account and associate it with the process, assembly, or department to which it is applicable (10/day).");
                System.out.println("6. Enter a new job, given its job-no, assembly-id, process-id, and date the job commenced (50/day).");
                System.out.println("7. At the completion of a job, enter the date it was completed and the information relevant to the type of job (50/day).");
                System.out.println("8. Enter a transaction-no and its sup-cost and update all the costs (details) of the affected accounts by adding sup-cost to their current values of details (50/day).");
                System.out.println("9. Retrieve the total cost incurred on an assembly-id (200/day).");
                System.out.println("10. Retrieve the total labor time within a department for jobs completed in the department during a given date (20/day).");
                System.out.println("11. Retrieve the processes through which a given assembly-id has passed so far (in date commenced order) and the department responsible for each process (100/day).");
                System.out.println("12. Retrieve the customers (in name order) whose category is in a given range (100/day).");
                System.out.println("13. Delete all cut jobs whose job-no is in a given range (1/month)");
                System.out.println("14. Change the color of a given paint job (1/week)");
                System.out.println("15. Import: enter new customers from a data file until the file is empty " +
                        "(the user must be asked to enter the input file name).");
                System.out.println("16. Export: Retrieve the customers (in name order) whose category is in a given " +
                        "range and output them to a data file instead of screen (the user must be asked to enter the output file name).");
                System.out.println("17. Exit");
                String choice = keyboard.nextLine();
                switch (choice) {
                    case "1":
                        System.out.println("Name: ");
                        String name = keyboard.nextLine();
                        System.out.println("Address: ");
                        String address = keyboard.nextLine();
                        System.out.println("Category: ");
                        String category = keyboard.nextLine();
                        boolean result = customerDao.add(name, address, category);
                        if (result) {
                            System.out.println("Add the customer successfully");
                        } else {
                            System.out.println("Add the customer fail");
                        }
                        break;
                    case "2":
                        System.out.println("Department data: ");
                        String departmentData = keyboard.nextLine();
                        result = departmentDao.add(departmentData);
                        if (result) {
                            System.out.println("Add the department successfully");
                        } else {
                            System.out.println("Add the department fail");
                        }
                        break;
                    case "3":
                        System.out.println("Enter the department id: ");
                        int departmentId;
                        try {
                            departmentId = Integer.parseInt(keyboard.nextLine());
                        } catch (Exception e) {
                            System.out.println("Invalid department id");
                            break;
                        }

                        System.out.println("Enter a process data: ");
                        String processData = keyboard.nextLine();

                        System.out.println("Choose type process: ");
                        System.out.println("1. Fit process");
                        System.out.println("2. Cut process");
                        System.out.println("3. Paint process");
                        String processType = keyboard.nextLine();
                        Process process;
                        while (true) {
                            if ("1".equals(processType)) {
                                System.out.println("Enter a fit type: ");
                                String fitType = keyboard.nextLine();
                                process = new FitProcess(processData, departmentId, fitType);
                                break;
                            } else if ("2".equals(processType)) {
                                System.out.println("Enter a cutting type: ");
                                String cuttingType = keyboard.nextLine();

                                System.out.println("Enter a machine type: ");
                                String machineType = keyboard.nextLine();

                                process = new CutProcess(processData, departmentId, cuttingType, machineType);
                                break;
                            } else if ("3".equals(processType)) {
                                System.out.println("Enter a paint type: ");
                                String paintType = keyboard.nextLine();

                                System.out.println("Enter a paint method: ");
                                String paintMethod = keyboard.nextLine();

                                process = new PaintProcess(processData, departmentId, paintType, paintMethod);
                                break;
                            } else {
                                System.out.println("Please choose 1 or 2 or 3");
                            }
                        }
                        result = processDao.add(process);
                        if (result) {
                            System.out.println("Add the process successfully");
                        } else {
                            System.out.println("Add the process fail");
                        }
                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                    case "6":
                        break;
                    case "7":
                        break;
                    case "8":
                        break;
                    case "9":
                        break;
                    case "10":
                        break;
                    case "11":
                        break;
                    case "12":
                        break;
                    case "13":
                        break;
                    case "14":
                        break;
                    case "15":
                        break;
                    case "16":
                        break;
                    case "17":
                        System.out.println("The program has closed, thank you.");
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurs");
        } finally {
            if (keyboard != null) {
                keyboard.close();
            }
        }
    }
}
