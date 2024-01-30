package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class FakerData {
    private FakerData() {
    }

    public static String fakeDate() {
        var date = new Faker();
        return LocalDate.now().plusDays(date.number().numberBetween(3, 30)).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String fakeCity() {
        var city = new String[]{"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Элиста",
                "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ", "Казань",
                "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар",
                "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород",
                "Брянск", "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново", "Орёл", "Иркутск", "Калининград", "Калуга", "Кемерово",
                "Киров", "Кострома", "Курган", "Курск", "Санкт-Петербург", "Липецк", "Магадан", "Москва", "Мурманск", "Нижний Новогород",
                "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Пенза", "Псков", "Салехард", "Ростов-на-Дону", "Рязань", "Самара",
                "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск",
                "Ярославль", "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь"};
        return city[new Random().nextInt(city.length)];
    }

    public static String fakeName() {
        var fio = new Faker(new Locale("ru"));
        return fio.name().lastName() + " " + fio.name().firstName();
    }

    public static String fakePhone() {
        var phone = new Faker(new Locale("ru"));
        return phone.phoneNumber().phoneNumber();
    }

    public static String invalidPhone() {
        var phone = new Faker(new Locale("ru"));
        return phone.numerify("#");
    }

    public static class Registration {
        private Registration() {
        }

        public static User generateUser() {
            return new User(fakeCity(), fakeName(), fakePhone());
        }
    }

    @Value
    public static class User {
        String city;
        String name;
        String phone;
    }
}