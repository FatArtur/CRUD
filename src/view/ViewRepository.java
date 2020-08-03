package view;

import controller.ControllerRepository;

import java.util.Scanner;

public class ViewRepository {

    private String viewMenu = "\nВыберите действие для Skill: \n" +
            "1.Создать\n" +
            "2.Удалить\n" +
            "3.Получить по ID\n" +
            "4.Получить все\n" +
            "5.Изменить\n" +
            "6.Выход\n";

    private Scanner scan;
    private ControllerRepository controller = new ControllerRepository();

    public void run() {
        boolean istrue = false;
        while (!istrue) {
            System.out.print(viewMenu);
            System.out.println("*******************");
            scan = new Scanner(System.in);
            String txt = scan.next();
            try {
                switch (txt) {
                    case "1":
                        System.out.println("Введите новый Skill");
                        txt = scan.next();
                        controller.create(txt);
                        break;
                    case "2":
                        System.out.println("Введите номер удаляемого ID");
                        txt = scan.next();
                        controller.delete(txt);
                        break;
                    case "3":
                        System.out.println("Введите номер получаемого ID");
                        txt = scan.next();
                        controller.getByID(txt);
                        break;
                    case "4":
                        System.out.println("Все имеющиеся Skill:");
                        controller.getAll();
                        break;
                    case "5":
                        System.out.println("Введите номер ID");
                        txt = scan.next();
                        String txt2 = scan.next();
                        controller.update(txt,txt2);
                        break;
                    case "6":
                        istrue = true;
                        break;
                    default:
                        System.out.println("Введено не корректное значение");
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }
}
