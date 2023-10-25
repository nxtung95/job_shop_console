package jop_shop;

import jop_shop.dao.*;
import jop_shop.model.Process;
import jop_shop.model.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static CustomerDao customerDao = new CustomerDao();
    private static DepartmentDao departmentDao = new DepartmentDao();
    private static ProcessDao processDao = new ProcessDao();
    private static JobDao jobDao = new JobDao();
    private static AccountDao accountDao = new AccountDao();
    private static AssemblyDao assemblyDao = new AssemblyDao();

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
                        handleOption1(keyboard);
                        break;
                    case "2":
                        handleOption2(keyboard);
                        break;
                    case "3":
                        handleOption3(keyboard);
                        break;
                    case "4":
                        handleOption4(keyboard);
                        break;
                    case "5":
                        handleOption5(keyboard);
                        break;
                    case "6":
                        handleOption6(keyboard);
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

    private static void handleOption6(Scanner keyboard) {
        System.out.println("Enter a new job");

        System.out.println("1. Fit job");
        System.out.println("2. Cut job");
        System.out.println("3. Paint job");
        System.out.println("Choose type job: ");
        String jobType = keyboard.nextLine();

        printAssemblyData();
        String againMessage = "Enter the assembly id: ";
        System.out.println(againMessage);
        int assemblyId = inputNumber(keyboard, againMessage);

        printProcessData();
        againMessage = "Enter the process id: ";
        System.out.println(againMessage);
        int processId = inputNumber(keyboard, againMessage);

        againMessage = "Enter a commenced date (yyyy/MM/dd): ";
        System.out.println(againMessage);
        Date commendDate = inputDate(keyboard, againMessage);

        againMessage = "Enter a labor time (hours): ";
        int laborTime = inputNumber(keyboard, againMessage);

        Job job = null;
        while (true) {
            if ("1".equals(jobType)) {
                job = new FitJob(null, commendDate, processId, laborTime);
            } else if ("2".equals(jobType)) {
                System.out.println("Enter a machine type: ");
                String machineType = keyboard.nextLine();

                againMessage = "Enter a machine used time (hours): ";
                System.out.println(againMessage);
                int machineUsedTime = inputNumber(keyboard, againMessage);

                System.out.println("Enter a material used: ");
                String materialUsed = keyboard.nextLine();

                job = new CutJob(null, commendDate, processId, laborTime, machineType, machineUsedTime, materialUsed);
            } else if ("3".equals(jobType)) {
                System.out.println("Enter a color: ");
                String color = keyboard.nextLine();

                againMessage = "Enter a volume: ";
                System.out.println(againMessage);
                int volume = inputNumber(keyboard, againMessage);

                job = new PaintJob(null, commendDate, processId, laborTime, color, volume);
            }
            if (job == null) {
                System.out.println("Invalid job type");
                System.out.println("1. Fit job");
                System.out.println("2. Cut job");
                System.out.println("3. Paint job");
                System.out.println("Choose type job: ");
                jobType = keyboard.nextLine();
            } else {
                jobDao.add(job);
            }
        }

    }

    private static void handleOption5(Scanner keyboard) {
        System.out.println("Enter a new account");
        while (true) {
            System.out.println("1. Assembly account");
            System.out.println("2. Process account");
            System.out.println("3. Department account");
            System.out.println("Choose type account: ");
            String accountType = keyboard.nextLine();

            String accountNo;
            while (true) {
                System.out.println("Enter an account no: ");
                accountNo = keyboard.nextLine();
                if (accountDao.checkExistAccountNo(accountNo)) {
                    System.out.println("This account no have existed on the system, please enter again");
                } else {
                    break;
                }
            }

            String againName = "Enter a cost: ";
            System.out.println(againName);
            double cost = inputDecimal(keyboard, againName);

            Date establishedDate = new Date(new java.util.Date().getTime());
            Account account;
            if ("1".equals(accountType)) {
                printAssemblyData();
                againName = "Enter the assembly id: ";
                System.out.println(againName);
                int assemblyId = inputNumber(keyboard, againName);
                account = new AssemblyAccount(accountNo, establishedDate, cost, assemblyId);
            } else if ("2".equals(accountType)) {
                printProcessData();
                againName = "Enter the process id: ";
                System.out.println(againName);
                int processId = inputNumber(keyboard, againName);
                account = new ProcessAccount(accountNo, establishedDate, cost, processId);
            } else if ("3".equals(accountType)) {
                printDepartmentData();
                againName = "Enter the department id: ";
                System.out.println(againName);
                int departmentId = inputNumber(keyboard, againName);
                account = new DepartmentAccount(accountNo, establishedDate, cost, departmentId);
            } else {
                System.out.println("Invalid account type");
                continue;
            }
            boolean result = accountDao.add(account);
            if (result) {
                System.out.println("Add account successfully");
            } else {
                System.out.println("Add account fail");
            }
            break;
        }

    }

    private static void printAssemblyData() {
        System.out.println("The list assembly: ");
        System.out.println("Assembly_Id---Assembly_Detail");
        List<Assembly> assemblyList = assemblyDao.findAll();
        for (Assembly assembly : assemblyList) {
            System.out.println(assembly.getAssemblyId() + "---" + assembly.getAssemblyDetail());
        }
    }

    private static double inputDecimal(Scanner keyboard, String againName) {
        while (true) {
            try {
                String costStr = keyboard.nextLine();
                return Double.parseDouble(costStr);
            } catch (Exception e) {
                System.out.println("Invalid cost");
                System.out.println(againName);
            }
        }
    }

    private static void handleOption4(Scanner keyboard) {
        System.out.println("Enter a new assembly: ");
        System.out.println("Enter an assembly detail: ");
        String assemblyDetail = keyboard.nextLine();

        printCustomerData();

        String againMessage = "Enter a customer id: ";
        System.out.println(againMessage);
        int customerId = inputNumber(keyboard, againMessage);

        Date orderDate = new Date(new java.util.Date().getTime());
        Assembly assembly = new Assembly(orderDate, assemblyDetail, customerId);

        while (true) {
            printProcessData();
            againMessage = "Choose a process id: ";
            System.out.println(againMessage);
            int processId = inputNumber(keyboard, againMessage);

            while (true) {
                System.out.println("This assembly associates one or more processes through one or more jobs. You need to enter jobs: ");
                System.out.println("1. Fit job");
                System.out.println("2. Cut job");
                System.out.println("3. Paint job");
                System.out.println("Choose type job: ");
                String jobType = keyboard.nextLine();
                Job job = null;

                againMessage = "Enter a commenced date (yyyy/MM/dd): ";
                System.out.println(againMessage);
                Date commendDate = inputDate(keyboard, againMessage);

                againMessage = "Enter a labor time (hours): ";
                int laborTime = inputNumber(keyboard, againMessage);

                if ("1".equals(jobType)) {
                    job = new FitJob(null, commendDate, processId, laborTime);
                } else if ("2".equals(jobType)) {
                    System.out.println("Enter a machine type: ");
                    String machineType = keyboard.nextLine();

                    againMessage = "Enter a machine used time (hours): ";
                    System.out.println(againMessage);
                    int machineUsedTime = inputNumber(keyboard, againMessage);

                    System.out.println("Enter a material used: ");
                    String materialUsed = keyboard.nextLine();

                    job = new CutJob(null, commendDate, processId, laborTime, machineType, machineUsedTime, materialUsed);
                } else if ("3".equals(jobType)) {
                    System.out.println("Enter a color: ");
                    String color = keyboard.nextLine();

                    againMessage = "Enter a volume: ";
                    System.out.println(againMessage);
                    int volume = inputNumber(keyboard, againMessage);

                    job = new PaintJob(null, commendDate, processId, laborTime, color, volume);
                }
                if (job != null) {
                    boolean result = jobDao.add(assembly, job);
                    if (result) {
                        System.out.println("Add the assembly and the job successfully");
                    } else {
                        System.out.println("Add the assembly and the job fail");
                    }

                    String jobChoice = keyboard.nextLine();
                    System.out.println("Do you need add more job for this process? (Y/N)");
                    if ("N".equalsIgnoreCase(jobChoice)) {
                        break;
                    }
                }
            }
            String processChoice = keyboard.nextLine();
            System.out.println("Do you need add more processes for this assembly? (Y/N)");
            if ("N".equalsIgnoreCase(processChoice)) {
                break;
            }
        }
    }

    private static int inputNumber(Scanner keyboard, String againMessage) {
        while (true) {
            int data;
            try {
                data = Integer.parseInt(keyboard.nextLine());
                return data;
            } catch (Exception e) {
                System.out.println("Invalid data, please enter again");
                System.out.println(againMessage);
            }
        }
    }

    private static Date inputDate(Scanner keyboard, String againMessage) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        while (true) {
            try {
                String date = keyboard.nextLine();
                Date parseDate = new Date(simpleDateFormat.parse(date).getTime());
                return parseDate;
            } catch (Exception e) {
                System.out.println("Invalid format date, please enter again. (yyyy/MM/dd): ");
                System.out.println(againMessage);
            }
        }
    }

    private static void handleOption3(Scanner keyboard) {
        System.out.println("Choose type process: ");
        System.out.println("1. Fit process");
        System.out.println("2. Cut process");
        System.out.println("3. Paint process");
        String processType = keyboard.nextLine();

        printDepartmentData();

        String againMessage = "Enter the department id: ";
        System.out.println(againMessage);
        int departmentId = inputNumber(keyboard, againMessage);

        System.out.println("Enter a process data: ");
        String processData = keyboard.nextLine();

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
                System.out.println("1. Fit process");
                System.out.println("2. Cut process");
                System.out.println("3. Paint process");
                System.out.println("Choose type process: ");
                processType = keyboard.nextLine();
            }
        }
        boolean result = processDao.add(process);
        if (result) {
            System.out.println("Add the process successfully");
        } else {
            System.out.println("Add the process fail");
        }
    }

    private static void handleOption2(Scanner keyboard) {
        System.out.println("Department data: ");
        String departmentData = keyboard.nextLine();
        boolean result = departmentDao.add(departmentData);
        if (result) {
            System.out.println("Add the department successfully");
        } else {
            System.out.println("Add the department fail");
        }
    }

    private static void handleOption1(Scanner keyboard) {
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
    }

    private static void printProcessData() {
        System.out.println("The list process: ");
        System.out.println("Process_ID---Process_Type---Process_Data");
        List<Process> processList = processDao.findAll();
        for (Process process : processList) {
            String processType;
            if (process instanceof FitProcess) {
                processType = "Fit Process";
            } else if (process instanceof CutProcess) {
                processType = "Cut Process";
            } else {
                processType = "Paint Process";
            }

            System.out.println(process.getProcessId() + "---" + processType + "---" + process.getProcessData());
        }
    }

    private static void printCustomerData() {
        System.out.println("The list customer: ");
        System.out.println("Customer_Id---Customer_Name---Customer_Address");
        List<Customer> customerList = customerDao.findAll();
        for (Customer customer : customerList) {
            System.out.println(customer.getCustomerId() + "---" + customer.getName() + "---" + customer.getAddress());
        }
    }

    private static void printDepartmentData() {
        System.out.println("The list department: ");
        List<Department> departmentList = departmentDao.findAll();
        System.out.println("Department_Id--------------------Department_Data");
        for (Department department : departmentList) {
            System.out.println(department.getDepartmentId() + "---" + department.getDepartmentData());
        }
    }
}
