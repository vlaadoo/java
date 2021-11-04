package hotel.service;

import hotel.model.Reservation;
import hotel.model.RoomInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;


public class Menu {
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    private static final HotelService hotelResource = HotelService.getSingleton();

    public static void mainMenu() {
        String line = "";
        Scanner scanner = new Scanner(System.in);

        printMainMenu();
        FillHotel.createRoom();
        try {
            do {
                line = scanner.nextLine();

                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1':
                            findAndReserveRoom();
                            break;
                        case '2':
                            seeMyReservation();
                            break;
                        case '3':
                            createAccount();
                            break;
                        case '4':
                            System.out.println("Выход");
                            break;
                        default:
                            System.out.println("Неправильный пункт меню\n");
                            break;
                    }
                } else {
                    System.out.println("Ошибка: Недопустимое действие\n");
                }
            } while (line.charAt(0) != '4' || line.length() != 1);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Получен пустой ввод. Выход из программы...");
        }
    }

    private static void findAndReserveRoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Введите дату заезда, дд/мм/гггг (пример: 01/12/2020)");
        Date checkIn = getInputDate(scanner);

        System.out.println("Введите дату выезда дд/мм/гггг (пример: 08/12/2020)");
        Date checkOut = getInputDate(scanner);

        if (checkIn != null && checkOut != null) {
            Collection<RoomInfo> availableRooms = hotelResource.findARoom(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                Collection<RoomInfo> alternativeRooms = hotelResource.findAlternativeRooms(checkIn, checkOut);

                if (alternativeRooms.isEmpty()) {
                    System.out.println("Номеров не найдено.");
                } else {
                    final Date alternativeCheckIn = hotelResource.addDefaultPlusDays(checkIn);
                    final Date alternativeCheckOut = hotelResource.addDefaultPlusDays(checkOut);
                    System.out.println("Мы нашли номера только на другие даты:" +
                            "\nДата заезда:" + alternativeCheckIn +
                            "\nДата отъезда:" + alternativeCheckOut);

                    printRooms(alternativeRooms);
                    reserveRoom(scanner, alternativeCheckIn, alternativeCheckOut, alternativeRooms);
                }
            } else {
                printRooms(availableRooms);
                reserveRoom(scanner, checkIn, checkOut, availableRooms);
            }
        }
    }

    private static Date getInputDate(final Scanner scanner) {
        try {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Ошибка: Неверная дата.");
            findAndReserveRoom();
        }

        return null;
    }

    private static void reserveRoom(final Scanner scanner, final Date checkInDate,
                                    final Date checkOutDate, final Collection<RoomInfo> rooms) {
        System.out.println("Хотите забронировать номер? y/n");
        final String bookRoom = scanner.nextLine();

        if ("y".equals(bookRoom)) {
            System.out.println("У вас есть учетная запись? y/n");
            final String haveAccount = scanner.nextLine();

            if ("y".equals(haveAccount)) {
                System.out.println("Введите адрес электронной почты (пример: name@domain.com)");
                final String customerEmail = scanner.nextLine();

                if (hotelResource.getCustomer(customerEmail) == null) {
                    System.out.println("Аккаунт не найден.\nВозможно, вам потребуется создать новую учетную запись.");
                } else {
                    System.out.println("Какой номер вы хотели бы забронировать?");
                    final String roomNumber = scanner.nextLine();

                    if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        final RoomInfo room = hotelResource.getRoom(roomNumber);

                        final Reservation reservation = hotelResource
                                .bookARoom(customerEmail, room, checkInDate, checkOutDate);
                        System.out.println("Забронировано!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Ошибка: номер недоступен.\nНачните бронирование заново.");
                    }
                }

                printMainMenu();
            } else {
                System.out.println("Пожалуйста, сперва создайте аккаунт");
                printMainMenu();
            }
        } else if ("n".equals(bookRoom)) {
            printMainMenu();
        } else {
            reserveRoom(scanner, checkInDate, checkOutDate, rooms);
        }
    }

    private static void printRooms(final Collection<RoomInfo> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("Номеров не найдено.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void seeMyReservation() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Введите адрес электронной почты (пример: name@domain.com)");
        final String customerEmail = scanner.nextLine();

        printReservations(hotelResource.getCustomersReservations(customerEmail));
    }

    private static void printReservations(final Collection<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("Бронирований не найдено");
        } else {
            reservations.forEach(reservation -> System.out.println("\n" + reservation));
        }
    }

    private static void createAccount() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Введите адрес электронной почты (пример: name@domain.com)");
        final String email = scanner.nextLine();

        System.out.println("Имя:");
        final String firstName = scanner.nextLine();

        System.out.println("Фамилия:");
        final String lastName = scanner.nextLine();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Аккаунт создан успешно!!");

            printMainMenu();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAccount();
        }
    }

    public static void printMainMenu() {
        System.out.print("--------------------------------------------\n" +
                "1. Найти и забронировать номер\n" +
                "2. Посмотреть мою бронь\n" +
                "3. Создать аккаунт\n" +
                "4. Выход\n" +
                "--------------------------------------------\n" +
                "Выберите пункт меню: ");
    }
}