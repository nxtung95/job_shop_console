package jop_shop;

import jop_shop.dao.*;
import jop_shop.model.Process;
import jop_shop.model.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static CustomerDao customerDao = new CustomerDao();
    private static DepartmentDao departmentDao = new DepartmentDao();
    private static ProcessDao processDao = new ProcessDao();
    private static JobDao jobDao = new JobDao();
    private static AccountDao accountDao = new AccountDao();
    private static AssemblyDao assemblyDao = new AssemblyDao();
    private static TransactionDao transactionDao = new TransactionDao();

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
                        handleOption7(keyboard);
                        break;
                    case "8":
                        handleOption8(keyboard);
                        break;
                    case "9":
                        handleOption9(keyboard);
                        break;
                    case "10":
                        handleOption10(keyboard);
                        break;
                    case "11":
                        handleOption11(keyboard);
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

    private static void handleOption11(Scanner keyboard) {
        List<Assembly> assemblyList = assemblyDao.findAll();
        printAssemblyData(assemblyList);
        Assembly assembly = inputAssembly(keyboard, assemblyList);
        int assemblyId = assembly.getAssemblyId();

        List<Process> processList = processDao.finAllByAssemblyAndJobCompleted(assemblyId);
    }

    private static void handleOption10(Scanner keyboard) {
        List<Department> departmentList = departmentDao.findAll();
        printDepartmentData(departmentList);
        Department department = inputDepartment(keyboard, departmentList);

        String againMessage = "Enter a completed date (dd-MM-yyyy): ";
        System.out.println(againMessage);
        Date completedDate = inputDate(keyboard, againMessage);

        int totalLaborTime = jobDao.getTotalLaborTime(department.getDepartmentId(), completedDate);
        System.out.println("The total labor time within a department and during a given date is: " + totalLaborTime);
    }

    private static void handleOption9(Scanner keyboard) {
        List<Assembly> assemblyList = assemblyDao.findAll();
        printAssemblyData(assemblyList);
        Assembly assembly = inputAssembly(keyboard, assemblyList);

        double totalCost = accountDao.getTotalCostByAssemblyId(assembly.getAssemblyId());
        System.out.println("The total cost incurred on an assembly-id " + assembly.getAssemblyId() + " is: " + totalCost);
    }

    private static void handleOption8(Scanner keyboard) {
        List<Transaction> transactionList = transactionDao.findAll();
        printTransactionData(transactionList);
        Transaction transaction = inputTransaction(keyboard, transactionList);

        String againMessage = "Enter a supplied cost: ";
        System.out.println(againMessage);
        double suppliedCost = inputDecimal(keyboard, againMessage);

        boolean result = accountDao.updateSuppliedCost(transaction, suppliedCost);
        if (result) {
            System.out.println("Update the supplied cost successfully");
        } else {
            System.out.println("Update the supplied cost fail");
        }
    }

    private static void handleOption7(Scanner keyboard) {
        List<Job> jobList = jobDao.findAll();
        printJobData(jobList);
        Job job = inputJob(keyboard, jobList);
        int jobNumber = job.getJobNumber();

        String againMessage = "Enter a completed date (dd-MM-yyyy): ";
        System.out.println(againMessage);
        Date completedDate = inputDate(keyboard, againMessage);

        boolean result = jobDao.updateCompletedDate(jobNumber, completedDate);
        if (result) {
            System.out.println("Update the completed date of the job successfully");
        } else {
            System.out.println("Update the completed date of the job fail");
        }
    }

    private static void handleOption6(Scanner keyboard) {
        System.out.println("Enter a new job");
        String jobType;
        while (true) {
            System.out.println("1. Fit job");
            System.out.println("2. Cut job");
            System.out.println("3. Paint job");
            System.out.println("Choose type job: ");
            jobType = keyboard.nextLine();
            if (!Arrays.asList("1,2,3".split(",")).contains(jobType)) {
                System.out.println("Invalid job type, please try again");
            } else {
                break;
            }
        }

        List<Assembly> assemblyList = assemblyDao.findAll();
        printAssemblyData(assemblyList);
        Assembly assembly = inputAssembly(keyboard, assemblyList);
        int assemblyId = assembly.getAssemblyId();

        List<Process> processList = processDao.findAll();
        printProcessData(processList);
        Process process = inputProcess(keyboard, processList);
        int processId = process.getProcessId();

        String againMessage = "Enter a commenced date (dd-MM-yyyy): ";
        System.out.println(againMessage);
        Date commendDate = inputDate(keyboard, againMessage);

        againMessage = "Enter a labor time (hours): ";
        int laborTime = inputNumber(keyboard, againMessage);

        Job job = null;
        while (true) {
            if ("1".equals(jobType)) {
                job = new FitJob(null, commendDate, assemblyId, processId, laborTime);
            } else if ("2".equals(jobType)) {
                System.out.println("Enter a machine type: ");
                String machineType = keyboard.nextLine();

                againMessage = "Enter a machine used time (hours): ";
                System.out.println(againMessage);
                int machineUsedTime = inputNumber(keyboard, againMessage);

                System.out.println("Enter a material used: ");
                String materialUsed = keyboard.nextLine();

                job = new CutJob(null, commendDate, assemblyId, processId, laborTime, machineType, machineUsedTime, materialUsed);
            } else if ("3".equals(jobType)) {
                System.out.println("Enter a color: ");
                String color = keyboard.nextLine();

                againMessage = "Enter a volume: ";
                System.out.println(againMessage);
                int volume = inputNumber(keyboard, againMessage);

                job = new PaintJob(null, commendDate, assemblyId, processId, laborTime, color, volume);
            }
            jobDao.add(job);
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
            if (!Arrays.asList("1,2,3".split(",")).contains(accountType)) {
                System.out.println("Invalid account type, try enter again");
                continue;
            }

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
                List<Assembly> assemblyList = assemblyDao.findAll();
                printAssemblyData(assemblyList);
                Assembly assembly = inputAssembly(keyboard, assemblyList);
                int assemblyId = assembly.getAssemblyId();

                account = new AssemblyAccount(accountNo, establishedDate, cost, assemblyId);
            } else if ("2".equals(accountType)) {
                List<Process> processList = processDao.findAll();
                printProcessData(processList);
                Process process = inputProcess(keyboard, processList);
                int processId = process.getProcessId();
                account = new ProcessAccount(accountNo, establishedDate, cost, processId);
            } else if ("3".equals(accountType)) {
                List<Department> departmentList = departmentDao.findAll();
                printDepartmentData(departmentList);
                Department department = inputDepartment(keyboard, departmentList);
                int departmentId = department.getDepartmentId();

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

    private static void handleOption4(Scanner keyboard) {
        System.out.println("Enter a new assembly");

        System.out.println("Enter an assembly detail: ");
        String assemblyDetail = keyboard.nextLine();

        List<Customer> customerList = customerDao.findAll();
        printCustomerData(customerList);
        Customer customer = inputCustomer(keyboard, customerList);
        int customerId = customer.getCustomerId();

        Date orderDate = new Date(new java.util.Date().getTime());
        Assembly assembly = new Assembly(orderDate, assemblyDetail, customerId);

        while (true) {
            List<Process> processList = processDao.findAll();
            printProcessData(processList);
            Process process = inputProcess(keyboard, processList);
            int processId = process.getProcessId();

            while (true) {
                System.out.println("This assembly associates one or more processes through one or more jobs. You need to enter jobs: ");
                System.out.println("1. Fit job");
                System.out.println("2. Cut job");
                System.out.println("3. Paint job");
                System.out.println("Choose type job: ");
                String jobType = keyboard.nextLine();
                Job job = null;

                String againMessage = "Enter a commenced date (dd-MM-yyyy): ";
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

    private static void handleOption3(Scanner keyboard) {
        System.out.println("Choose type process: ");
        System.out.println("1. Fit process");
        System.out.println("2. Cut process");
        System.out.println("3. Paint process");
        String processType = keyboard.nextLine();

        List<Department> departmentList = departmentDao.findAll();
        printDepartmentData(departmentList);
        Department department = inputDepartment(keyboard, departmentList);
        int departmentId = department.getDepartmentId();

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        while (true) {
            try {
                String date = keyboard.nextLine();
                Date parseDate = new Date(simpleDateFormat.parse(date).getTime());
                return parseDate;
            } catch (Exception e) {
                System.out.println("Invalid format date, please enter again. (dd-MM-yyyy): ");
                System.out.println(againMessage);
            }
        }
    }

    private static Job inputJob(Scanner keyboard, List<Job> jobList) {
        while (true) {
            System.out.println("Enter the job id: ");
            int jobNumber;
            try {
                jobNumber = Integer.parseInt(keyboard.nextLine());

                Optional<Job> optionalJob = jobList.stream().filter(c -> c.getJobNumber() == jobNumber).findFirst();
                if (!optionalJob.isPresent()) {
                    System.out.println("The job id is not in the list, please enter again");
                    continue;
                }
                return optionalJob.get();
            } catch (Exception e) {
                System.out.println("Invalid job id, please enter again");
            }
        }
    }

    private static void printAssemblyData(List<Assembly> assemblyList) {
        System.out.println("The list assembly: ");
        System.out.println("Assembly_Id---Assembly_Detail");
        for (Assembly assembly : assemblyList) {
            System.out.println(assembly.getAssemblyId() + "---" + assembly.getAssemblyDetail());
        }
    }

    private static Transaction inputTransaction(Scanner keyboard, List<Transaction> transactionList) {
        while (true) {
            System.out.println("Enter the transaction number: ");
            int transactionNumber;
            try {
                transactionNumber = Integer.parseInt(keyboard.nextLine());

                Optional<Transaction> optionalTransaction = transactionList.stream().filter(c -> c.getTransactionNumber() == transactionNumber).findFirst();
                if (!optionalTransaction.isPresent()) {
                    System.out.println("The transaction number is not in the list, please enter again");
                    continue;
                }
                return optionalTransaction.get();
            } catch (Exception e) {
                System.out.println("Invalid transaction number, please enter again");
            }
        }
    }

    private static Assembly inputAssembly(Scanner keyboard, List<Assembly> assemblyList) {
        while (true) {
            System.out.println("Enter the assembly id: ");
            int assemblyId;
            try {
                assemblyId = Integer.parseInt(keyboard.nextLine());

                Optional<Assembly> optionalAssembly = assemblyList.stream().filter(c -> c.getAssemblyId() == assemblyId).findFirst();
                if (!optionalAssembly.isPresent()) {
                    System.out.println("The assembly id is not in the list, please enter again");
                    continue;
                }
                return optionalAssembly.get();
            } catch (Exception e) {
                System.out.println("Invalid assembly id, please enter again");
            }
        }
    }

    private static Process inputProcess(Scanner keyboard, List<Process> processList) {
        while (true) {
            System.out.println("Enter a process id: ");
            int processId;
            try {
                processId = Integer.parseInt(keyboard.nextLine());

                Optional<Process> optionalProcess = processList.stream().filter(c -> c.getProcessId() == processId).findFirst();
                if (!optionalProcess.isPresent()) {
                    System.out.println("The process id is not in the list, please enter again");
                    continue;
                }
                return optionalProcess.get();
            } catch (Exception e) {
                System.out.println("Invalid process id, please enter again");
            }
        }
    }

    private static Customer inputCustomer(Scanner keyboard, List<Customer> customerList) {
        while (true) {
            System.out.println("Enter a customer id: ");
            int customerId;
            try {
                customerId = Integer.parseInt(keyboard.nextLine());

                Optional<Customer> optionalCustomer = customerList.stream().filter(c -> c.getCustomerId() == customerId).findFirst();
                if (!optionalCustomer.isPresent()) {
                    System.out.println("The customer id is not in the list, please enter again");
                    continue;
                }
                return optionalCustomer.get();
            } catch (Exception e) {
                System.out.println("Invalid customer id, please enter again");
            }
        }
    }

    private static Department inputDepartment(Scanner keyboard, List<Department> departmentList) {
        while (true) {
            System.out.println("Enter the department id: ");
            int departmentId;
            try {
                departmentId = Integer.parseInt(keyboard.nextLine());

                Optional<Department> optDepartment = departmentList.stream().filter(d -> d.getDepartmentId() == departmentId).findFirst();
                if (!optDepartment.isPresent()) {
                    System.out.println("The department id is not in the list, please enter again");
                    continue;
                }
                return optDepartment.get();
            } catch (Exception e) {
                System.out.println("Invalid department id, please enter again");
            }
        }
    }

    private static void printJobData(List<Job> jobList) {
        System.out.println("The list job: ");
        System.out.println("Job_Number---Job_Type");
        for (Job job : jobList) {
            String jobType;
            if (job instanceof FitJob) {
                jobType = "Fit Job";
            } else if (job instanceof CutJob) {
                jobType = "Cut Job";
            } else {
                jobType = "Paint Job";
            }

            System.out.println(job.getJobNumber() + "---" + jobType);
        }
    }

    private static void printProcessData(List<Process> processList) {
        System.out.println("The list process: ");
        System.out.println("Process_ID---Process_Type---Process_Data");
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

    private static void printCustomerData(List<Customer> customerList) {
        System.out.println("The list customer: ");
        System.out.println("Customer_Id---Customer_Name---Customer_Address");
        for (Customer customer : customerList) {
            System.out.println(customer.getCustomerId() + "---" + customer.getName() + "---" + customer.getAddress());
        }
    }

    private static void printTransactionData(List<Transaction> transactionList) {
        System.out.println("The list transaction: ");
        System.out.println("Transaction_Id---Supplied_Cost");
        for (Transaction transaction : transactionList) {
            System.out.println(transaction.getTransactionNumber() + "---" + transaction.getSuppliedCost());
        }
    }

    private static void printDepartmentData(List<Department> departmentList) {
        System.out.println("The list department: ");
        System.out.println("Department_Id--------------------Department_Data");
        for (Department department : departmentList) {
            System.out.println(department.getDepartmentId() + "---" + department.getDepartmentData());
        }
    }
}
