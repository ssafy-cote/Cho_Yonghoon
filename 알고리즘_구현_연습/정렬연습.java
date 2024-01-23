package test;

import java.util.Arrays;

public class 정렬연습 {
	public static void main(String[] args) {
		
		Employee[] emps = {
				new Employee("5","설한결","no.1", 5000),
				new Employee("2","남궁소라","no.2", 3000),
				new Employee("3","오버들","no.3", 1000),
				new Employee("1","탁아람","no.3", 1000),
				new Employee("7","권철", "no.4", 500),
				new Employee("4","유바다","no.4", 500),
				new Employee("6","풍우람","no.4", 500),
			};

			Arrays.sort(emps, (o1, o2) -> {
				if (o1.getLevel() == o2.getLevel()) {
					return o1.getName().compareTo(o2.getName());
				}
				return o1.getLevel().compareTo(o2.getLevel());
			});

			for (Employee e : emps) {
				System.out.println(e);
			}
	}
}
