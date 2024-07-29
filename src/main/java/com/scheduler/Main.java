package com.scheduler;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Job> jobs = new ArrayList<>();
        boolean done = false;

        // Job input
        System.out.println("Enter job details (ID, Priority, Burst Time). Type 'done' to finish:");
        while (!done) {
            try {
                System.out.print("Enter Job ID: ");
                String idInput = scanner.nextLine();
                if (idInput.equalsIgnoreCase("done")) {
                    done = true;
                    continue;
                }
                int id = Integer.parseInt(idInput);

                System.out.print("Enter Priority: ");
                int priority = scanner.nextInt();

                System.out.print("Enter Burst Time: ");
                int burstTime = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                jobs.add(new Job(id, priority, burstTime));

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid integers for Job ID.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid integers for Priority and Burst Time.");
                scanner.next();  // Clear the invalid input
            }
        }

        // Algorithm selection
        boolean continueRunning = true;
        while (continueRunning) {
            System.out.println("Select Scheduling Algorithm or type 'done' to exit:");
            System.out.println("1. FCFS");
            System.out.println("2. SJN");
            System.out.println("3. Round Robin");
            System.out.println("4. Priority Scheduling");
            System.out.print("Enter your choice: ");

            String choiceInput = scanner.nextLine();
            if (choiceInput.equalsIgnoreCase("done")) {
                continueRunning = false;
                continue;
            }

            Scheduler scheduler = null;
            try {
                int choice = Integer.parseInt(choiceInput);

                switch (choice) {
                    case 1:
                        scheduler = new FCFS(new ArrayList<>(jobs));
                        break;
                    case 2:
                        scheduler = new SJN(new ArrayList<>(jobs));
                        break;
                    case 3:
                        System.out.print("Enter Time Quantum: ");
                        int timeQuantum = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline character
                        scheduler = new RoundRobin(new ArrayList<>(jobs), timeQuantum);
                        break;
                    case 4:
                        scheduler = new PriorityScheduling(new ArrayList<>(jobs));
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid scheduling algorithm.");
                        continue;
                }

                // Start scheduler and wait for it to complete
                SchedulerThread schedulerThread = new SchedulerThread(scheduler);
                schedulerThread.start();
                schedulerThread.join(); // Wait for the thread to finish

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            } catch (InterruptedException e) {
                System.out.println("Scheduler thread interrupted.");
            }
        }
        scanner.close();
    }
}
