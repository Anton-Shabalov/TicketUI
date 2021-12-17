package resurses;

import java.util.ListResourceBundle;

public class Resources_sv extends ListResourceBundle implements Naming {

    private String name = "Swedish";

    @Override
    public String getName() {
        return name;
    }

    private static final Object[][] contents =
            {
                    {"Войти", "Att komma in"},
                    {"Зарегистрироваться", "Registrera nu"},
                    {"Авторизация", "Tillstånd"},
                    {"Логин", "Logga in"},
                    {"Пароль", "Lösenord"},
                    {"Назад", "Tillbaka till"},
                    {"Данные, которые вы ввели не действительны", "De uppgifter du angav är ogiltiga"},
                    {"Поле пароль не может быть пустым", "Lösenordsfältet får inte vara tomt"},
                    {"Поле логин не может быть пустым", "Inloggningsfältet får inte vara tomt"},
                    {"Командная строка", "Kommandorad"},
                    {"Таблица с элементами", "Tabell med element"},
                    {"Визуализация элементов", "Rendering element"},
                    {"Выход", "Produktion"},
                    {"", ""},
                    {"Привет", "Hej"},
                    {"Введите команду, для простотра всех комманд введите <help>", "Ange ett kommando om du vill visa alla kommandon anger du <help>"},
                    {"Вы ввели пустую строку", "Du angav en tom rad"},
                    {"Выполнить", "Kör"},
                    {"Ваш запрос", "Din förfrågan"},
                    {"Привет:", "Hej:"},
                    {"Ид", "Id"},
                    {"Название", "namn"},
                    {"Место", "En plats"},
                    {"Ряд", "Rad"},
                    {"Расположение", "Plats"},
                    {"Цена", "Pris"},
                    {"Скидка", "Rabatt"},
                    {"Тип билета", "Biljettyp"},
                    {"Место проведения", "Plats"},
                    {"Название заведения", "Anläggningens namn"},

                    {"Вместимость", "Kapacitet"},
                    {"Тип", "En typ"},
                    {"Адрес", "Adress"},
                    {"Индекс", "Index"},
                    {"X", "X"},
                    {"Y", "Y"},
                    {"Z", "Z"},
                    {"Дата создания", "skapelsedatum"},
                    {"Создатель", "Skaparen"},
                    {"Регистрация", "checka in"},
                    {"Пользователь с такими именем уже существует", "En användare med samma namn finns redan"},
                    {"Изменить", "Redigera"},
                    {"Название мероприятия", "händelsens titel"},
                    {"Тип заведения", "Anläggningstyp"},
                    {"Создатель:", "Skapare:"},
                    {"Пожалуйста, введите название мероприятия, поле не должно быть пустым.", "Ange namnet på händelsen, fältet får inte vara tomt."},
                    {"Вы введи пустую строку", "Du anger en tom rad"},
                    {"Пожалуйста, поочередно введите место и ряд , номер места должен быть больше -951 и представлен в виде целого числа, ряд в виде числа с плавающей точкой", "Ange platsen och raden en efter en, platsnumret måste vara större än -951 och representeras som ett heltal, raden som ett flytande nummer"},
                    {"Вами было введено не число", "Du skrev inte in ett nummer"},
                    {"Пожалуйста, введите цену билета, она может принимать числовое значение с плавающей точкой ", "Ange biljettpriset, det kan vara ett numeriskt värde för flytande punkt"},
                    {"Введеные вами данные не похожи на число, которое требовалось ввести", "De uppgifter du angav ser inte ut som det nummer du ombads att ange"},
                    {"Пожалуйста, введите размер скидки в формате целого числа превышающего 0 и непревышабшего 100", "Ange storleken på rabatten i formatet för ett heltal större än 0 och inte större än 100"},
                    {"Выберите из предложенного тип билета и введите его в командную строку VIP USUAL BUDGETARY CHEAP ", "Välj från den föreslagna biljettypen och ange den i kommandoraden VIP USUAL BUDGETARY CHEAP "},
                    {"Пожалуйста, выберите одно из предложенных значений", "Välj ett av de föreslagna värdena"},
                    {"Пожалуйста, введите название заведения, где будет происходит мероприятие, название не может быть пустым", "Ange namnet på den plats där evenemanget kommer att äga rum, namnet kan inte vara tomt"},
                    {"Вы нчиего не ввели", "Вы нчиего не ввели"},
                    {"Введите общее количество мест в виде целого чила", "Ange det totala antalet platser som ett heltal"},
                    {"Число должно быть больше нуля ", "Siffran måste vara större än noll"},
                    {"Выберите из предложенного тип билета и введите его в командную строку PUB OPEN_AREA MALL ", "Välj från den föreslagna biljettypen och ange den i kommandoradenPUB OPEN_AREA MALL "},
                    {"Введите индекс, его длина не должна превышать 20 символов , также он не может быть пустым", "Ange index, dess längd får inte överstiga 20 tecken och det kan inte vara tomt"},
                    {"Введите кординаты точки по осям x y z  поочередно, x и z могут принимать только целочисленные значения, у число с плавающей точкой", "Ange koordinaterna för punkten längs x y z-axlarna växelvis, x och z kan bara ta heltal, y är ett flytpunktsnummer"},
                    {"Введите номер поля, которое вы хотите изменить", "Ange numret på fältet du vill ändra"},
                    {"Название мероприятия - 1", "Händelsens namn - 1"},
                    {"Место                - 2", "Plats - 2"},
                    {"Цена                 - 3", "Pris - 3"},
                    {"Скидка               - 4", "Rabatt - 4"},
                    {"Тип билета           - 5", "Biljettyp - 5"},
                    {"Название заведения   - 6", "Anläggningens namn - 6"},
                    {"Вместимость          - 7", "Kapacitet - 7"},
                    {"Тип заведения        - 8", "Anläggningstyp - 8"},
                    {"Адрес заведения      - 9", "Anläggningsadress - 9"},
                    {"Введеные вами данные не соответствуют шаблону", "De uppgifter du angav matchar inte mönstret"},
                    {"Вы ввели невалидное значение. Для повтора выполните запрос update (id)!", "Du angav ett ogiltigt värde. Kör uppdateringsförfrågan för att försöka igen (id)!"},
                    {"К сожалению у вас нет прав на чтение этого файла", "Tyvärr har du inte behörighet att läsa den här filen."},
                    {"Файла с указанным именем не существует", "Filen med det angivna namnet finns inte"},
                    {"ключ может принимать только целое числовое значение ", "nyckeln kan bara ta ett heltal numeriskt värde"},
                    {"ключ может принимать только целое числовое значение, строго больше нуля ", "nyckeln kan bara ta ett heltal numeriskt värde, strikt större än noll"},
                    {"Такой команды не существует. Воскользуйтесь help для получения всех возможных команд", "Det finns inget sådant kommando. Använd hjälp för alla möjliga kommandon"},
                    {"данная команда принемает другое количество аргументов", "det här kommandot tar ett annat antal argument"},
                    {"Что-то произошло с вашим логином, авторизируйтесь еще раз", "Något hände med ditt användarnamn, logga in igen"},
                    {"Сервер временно недоступен, попробуйте позже", "Servern är tillfälligt otillgänglig. Försök igen senare"},
                    {"Соединение установлено", "Kontakt etablerad"},
                    {"Элемент успещно обновлен", "Objektet har uppdaterats"},
                    {"Z может быть только long", "Z kanske bara long"},
                    {"Y может быть только Double", "Y kanske bara  Double"},
                    {"X может быть только int", "X kanske bara int"},
                    {"Индекс не может быть больше 20 символов", "Indexet får inte innehålla mer än 20 tecken"},
                    {"Вместимость может быть строго больше 0", "Kapaciteten kan vara mer än 0"},
                    {"Вметсимость может быть только числом", "Passformen kan bara vara ett nummer"},
                    {"Скидка может быть от 0 до 100", "Rabatten kan vara från 0 till 100"},
                    {"Поле скидка может быть только Long", "Rabattfält kan bara varaLong"},
                    {"цена может быть только float", "priset kan bara vara float"},
                    {"Поле ряд может быть только Double", "Fältrad kan bara vara Double"},
                    {"Место может быть строго больше -951", "Platsen kan vara strikt större än -951"},
                    {"В поле место не int значение", "Det finns ingen plats i fältet int värde"},
                    {"Имя не может быть пустым", "Namnet kan inte vara tomt"},
                    {"Не все поля заполнены", "Inte alla fält är fyllda"},
                    {"У вас нет прав на изменение этого обьекта", "Du har inte behörighet att ändra detta objekt."},
                    {"Подключиться не удалось", "Uppkopplingen misslyckades"},
                    {"Попробуйте запустить программу позже", "Försök att köra programmet senare"},
            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}

