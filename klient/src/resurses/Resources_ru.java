package resurses;

import java.util.ListResourceBundle;

public class Resources_ru extends ListResourceBundle implements Naming{

    private String name = "Русский";

    @Override
    public String getName() {
        return name;
    }

    private static final Object[][] contents =
            {
                    {"Войти", "Войти"},
                    {"Зарегистрироваться", "Зарегистрироваться"},
                    {"Авторизация", "Авторизация"},
                    {"Логин", "Логин"},
                    {"Пароль", "Пароль"},
                    {"Назад", "Назад"},
                    {"Данные, которые вы ввели не действительны", "Данные, которые вы ввели не действительны"},
                    {"Поле пароль не может быть пустым", "Поле пароль не может быть пустым"},
                    {"Поле логин не может быть пустым", "Поле логин не может быть пустым"},
                    {"Командная строка", "Командная строка"},
                    {"Таблица с элементами", "Таблица с элементами"},
                    {"Визуализация элементов", "Визуализация элементов"},
                    {"Выход", "Выход"},
                    {"", ""},
                    {"Привет", "Привет"},
                    {"Введите команду, для простотра всех комманд введите <help>", "Введите команду, для простотра всех комманд введите <help>"},
                    {"Вы ввели пустую строку", "Вы ввели пустую строку"},
                    {"Выполнить", "Выполнить"},
                    {"Ваш запрос", "Ваш запрос"},
                    {"Привет:", "Привет:"},
                    {"Ид", "Ид"},
                    {"Название", "Название"},
                    {"Место", "Место"},
                    {"Ряд", "Ряд"},
                    {"Расположение", "Расположение"},
                    {"Цена", "Цена"},
                    {"Скидка", "Скидка"},
                    {"Тип билета", "Тип билета"},
                    {"Место проведения", "Место проведения"},
                    {"Название заведения", "Название заведения"},

                    {"Вместимость", "Вместимость"},
                    {"Тип", "Тип"},
                    {"Адрес", "Адрес"},
                    {"Индекс", "Индекс"},
                    {"X", "X"},
                    {"Y", "Y"},
                    {"Z", "Z"},
                    {"Дата создания", "Дата создания"},
                    {"Создатель", "Создатель"},
                    {"Регистрация", "Регистрация"},
                    {"Пользователь с такими именем уже существует", "Пользователь с такими именем уже существует"},
                    {"Изменить", "Изменить"},
                    {"Название мероприятия", "Название мероприятия"},
                    {"Тип заведения", "Тип заведения"},
                    {"Создатель:", "Создатель:"},
                    {"Пожалуйста, введите название мероприятия, поле не должно быть пустым.", "Пожалуйста, введите название мероприятия, поле не должно быть пустым."},
                    {"Вы введи пустую строку", "Вы введи пустую строку"},
                    {"Пожалуйста, поочередно введите место и ряд , номер места должен быть больше -951 и представлен в виде целого числа, ряд в виде числа с плавающей точкой", "Пожалуйста, поочередно введите место и ряд , номер места должен быть больше -951 и представлен в виде целого числа, ряд в виде числа с плавающей точкой"},
                    {"Вами было введено не число", "Вами было введено не число"},
                    {"Пожалуйста, введите цену билета, она может принимать числовое значение с плавающей точкой ", "Пожалуйста, введите цену билета, она может принимать числовое значение с плавающей точкой "},
                    {"Введеные вами данные не похожи на число, которое требовалось ввести", "Введеные вами данные не похожи на число, которое требовалось ввести"},
                    {"Пожалуйста, введите размер скидки в формате целого числа превышающего 0 и непревышабшего 100", "Пожалуйста, введите размер скидки в формате целого числа превышающего 0 и непревышабшего 100"},
                    {"Выберите из предложенного тип билета и введите его в командную строку VIP USUAL BUDGETARY CHEAP ", "Выберите из предложенного тип билета и введите его в командную строку VIP USUAL BUDGETARY CHEAP "},
                    {"Пожалуйста, выберите одно из предложенных значений", "Пожалуйста, выберите одно из предложенных значений"},
                    {"Пожалуйста, введите название заведения, где будет происходит мероприятие, название не может быть пустым", "Пожалуйста, введите название заведения, где будет происходит мероприятие, название не может быть пустым"},
                    {"Вы нчиего не ввели", "Вы нчиего не ввели"},
                    {"Введите общее количество мест в виде целого чила", "Введите общее количество мест в виде целого чила"},
                    {"Число должно быть больше нуля ", "Число должно быть больше нуля "},
                    {"Выберите из предложенного тип билета и введите его в командную строку PUB OPEN_AREA MALL ", "Выберите из предложенного тип билета и введите его в командную строку PUB OPEN_AREA MALL "},
                    {"Введите индекс, его длина не должна превышать 20 символов , также он не может быть пустым", "Введите индекс, его длина не должна превышать 20 символов , также он не может быть пустым"},
                    {"Введите кординаты точки по осям x y z  поочередно, x и z могут принимать только целочисленные значения, у число с плавающей точкой", "Введите кординаты точки по осям x y z  поочередно, x и z могут принимать только целочисленные значения, у число с плавающей точкой"},
                    {"Введите номер поля, которое вы хотите изменить", "Введите номер поля, которое вы хотите изменить"},
                    {"Название мероприятия - 1", "Название мероприятия - 1"},
                    {"Место                - 2", "Место                - 2"},
                    {"Цена                 - 3", "Цена                 - 3"},
                    {"Скидка               - 4", "Скидка               - 4"},
                    {"Тип билета           - 5", "Тип билета           - 5"},
                    {"Название заведения   - 6", "Название заведения   - 6"},
                    {"Вместимость          - 7", "Вместимость          - 7"},
                    {"Тип заведения        - 8", "Тип заведения        - 8"},
                    {"Адрес заведения      - 9", "Адрес заведения      - 9"},
                    {"Введеные вами данные не соответствуют шаблону", "Введеные вами данные не соответствуют шаблону"},
                    {"Вы ввели невалидное значение. Для повтора выполните запрос update (id)!", "Вы ввели невалидное значение. Для повтора выполните запрос update (id)!"},
                    {"К сожалению у вас нет прав на чтение этого файла", "К сожалению у вас нет прав на чтение этого файла"},
                    {"Файла с указанным именем не существует", "Файла с указанным именем не существует"},
                    {"ключ может принимать только целое числовое значение ", "ключ может принимать только целое числовое значение "},
                    {"ключ может принимать только целое числовое значение, строго больше нуля ", "ключ может принимать только целое числовое значение, строго больше нуля "},
                    {"Такой команды не существует. Воскользуйтесь help для получения всех возможных команд", "Такой команды не существует. Воскользуйтесь help для получения всех возможных команд"},
                    {"данная команда принемает другое количество аргументов", "данная команда принемает другое количество аргументов"},
                    {"Что-то произошло с вашим логином, авторизируйтесь еще раз", "Что-то произошло с вашим логином, авторизируйтесь еще раз"},
                    {"Сервер временно недоступен, попробуйте позже", "Сервер временно недоступен, попробуйте позже"},
                    {"Соединение установлено", "Соединение установлено"},
                    {"Элемент успещно обновлен", "Элемент успещно обновлен"},
                    {"Z может быть только long", "Z может быть только long"},
                    {"Y может быть только Double", "Y может быть только Double"},
                    {"X может быть только int", "X может быть только int"},
                    {"Индекс не может быть больше 20 символов", "Индекс не может быть больше 20 символов"},
                    {"Вместимость может быть строго больше 0", "Вместимость может быть строго больше 0"},
                    {"Вметсимость может быть только числом", "Вметсимость может быть только числом"},
                    {"Скидка может быть от 0 до 100", "Скидка может быть от 0 до 100"},
                    {"Поле скидка может быть только Long", "Поле скидка может быть только Long"},
                    {"цена может быть только float", "цена может быть только float"},
                    {"Поле ряд может быть только Double", "Поле ряд может быть только Double"},
                    {"Место может быть строго больше -951", "Место может быть строго больше -951"},
                    {"В поле место не int значение", "В поле место не int значение"},
                    {"Имя не может быть пустым", "Имя не может быть пустым"},
                    {"Не все поля заполнены", "Не все поля заполнены"},
                    {"У вас нет прав на изменение этого обьекта", "У вас нет прав на изменение этого обьекта"},
                    {"Подключиться не удалось", "Подключиться не удалось"},
                    {"Попробуйте запустить программу позже", "Попробуйте запустить программу позже"},


            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
