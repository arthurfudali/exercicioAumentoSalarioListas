package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		// setup inicial
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		// Criação da lista usando ArrayList e o objeto Employee
		List<Employee> employeeList = new ArrayList<>();

		System.out.println("How many employees will be registered? ");
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println("Employee #" + (i + 1));
			System.out.println("ID: ");
			int id = sc.nextInt();
			// metodo hasId verifica se o id já existe
			while (hasId(employeeList, id)) {
				System.out.println("Id already taken, try again: ");
				id = sc.nextInt();
			}
			System.out.println("Name: ");
			String name = sc.next();
			System.out.println("Salary: ");
			double salary = sc.nextDouble();
			// adiciona na lista as informações do objeto employee
			employeeList.add(new Employee(id, name, salary));
		}
		// printa as informações do Employee que estão na lista com base no toString
		// padrão
		for (Employee x : employeeList) {
			System.out.println(x);
		}
		System.out.println("Enter the employee that will have salary increase: ");
		int employeeId = sc.nextInt();
		// usa o filtro lambda para verificar se o Id existe
		Employee emp = employeeList.stream().filter(x -> x.getId() == employeeId).findFirst().orElse(null);
		if (emp == null) {
			System.out.println("This id does not exist!");
		} else {
			System.out.println("Enter the percentage: ");
			double percentage = sc.nextDouble();
			// chama a funcao increaseSalary com base no objeto já filtrado "emp"
			emp.increaseSalary(percentage);
		}
		System.out.println();
		System.out.println("List of employees: ");
		for (Employee x : employeeList) {
			System.out.println(x);
		}
		sc.close();

	}

	// criação do metodo hasId (outro filtro com base em pedricado lambda
	public static boolean hasId(List<Employee> employeeList, int id) {
		Employee emp = employeeList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return emp != null; // como o objetivo é verificar se o ID existe, ele só retorna o "emp" se ele
							// realmente existir
	}

}
