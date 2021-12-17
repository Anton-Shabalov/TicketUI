package resurses;

import java.util.ListResourceBundle;

public class Resources_cz extends ListResourceBundle {

    private String name = "Czech";


    private static final Object[][] contents =
            {
                    {"Билет", " Lístek "},
                    {"id:", " číslo "},
                    {"Билет на:", " Vstupenka na: "},
                    {"Место:", " Místo: "},
                    {"Дата создания:", " Datum vytvoření: "},
                    {"Цена:", " Cena: "},
                    {"Скидка:", " Sleva: "},
                    {"Тип билета:", " Typ lístku: "},
                    {"Место проведения:", " Umístění: "},
                    {"Ряд:", " Řádek: "},
                    {"Название заведения:", " Název zařízení: "},
                    {"Количество мест всего:", " Celkový počet sedadel: "},
                    {"Вид заведения", " Typ zařízení "},
                    {"Адрес:", " Adresa: "},
                    {"индекс:", " index: "},
                    {"Расположение", " Umístění: "},
                    {"Кордината по x:=", " Souřadnice X: = "},
                    {"Кордината по y=", " Souřadnice Y = "},
                    {"Кордината по z=", " Souřadnice podle z = "},

                    {"help                        : вывести справку по доступным командам", "help           : visa hjälp för tillgängliga kommandon"},
                    {"info                        : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", "info            : skriva ut information om samlingen (typ, initialiseringsdatum, antal element etc.) till standardutdata"},
                    {"show                        : вывести в стандартный поток вывода все элементы коллекции в строковом представлении", "show         : skriv ut alla element i samlingen med standardutdata i strängrepresentation"},
                    {"info                        : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", "info            : skriva ut information om samlingen (typ, initialiseringsdatum, antal element etc.) till standardutdata"},
                    {"show                        : вывести в стандартный поток вывода все элементы коллекции в строковом представлении", "show         : skriv ut alla element i samlingen med standardutdata i strängrepresentation"},
                    {"insert id                   : добавить новый элемент с заданным ключом", "insert id           : lägg till ett nytt objekt med den angivna nyckeln"},
                    {"update id                   : обновить значение элемента коллекции, id которого равен заданному", "update id          : uppdatera värdet på samlingselementet vars id är lika med det givna"},
                    {"remove_key id               : удалить элемент из коллекции по его ключу", "remove_key id          : ta bort ett objekt från samlingen med dess nyckel"},
                    {"clear                       : очистить коллекцию", "clear         : tydlig samling"},
                    {"execute_script file_name    : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.", "execute_script file_name          : läs och kör skriptet från den angivna filen. Skriptet innehåller kommandon i samma form där användaren matar in dem interaktivt."},
                    {"exit                        : завершить программу (без сохранения в файл)", "exit         : avsluta programmet (utan att spara i filen)"},
                    {"remove_lower id             : удалить из коллекции все элементы, меньшие, чем заданный", "remove_lower id         : ta bort alla element från samlingen som är mindre än den givna"},
                    {"history                     : вывести последние 15 команд (без их аргументов)", "history          : skriv ut de senaste 15 kommandona (utan deras argument)"},
                    {"remove_lower_key id         : удалить из коллекции все элементы, ключ которых меньше, чем заданный", "remove_lower_key id         : ta bort alla element vars nyckel är mindre än den givna från samlingen"},
                    {"sum_of_discount             : вывести сумму значений поля discount для всех элементов коллекции", "sum_of_discount            : visa summan av rabattfältets värden för alla element i samlingen"},
                    {"filter_contains_name name   : вывести элементы, значение поля name которых содержит заданную подстроку", "filter_contains_name name           : visa element vars namnfältvärde innehåller den angivna strängen"},
                    {"print_field_descending_type : вывести значения поля type всех элементов в порядке убывания", "print_field_descending_type : skriv ut värdena för typfältet för alla element i fallande ordning"},
                    {"Вы не имеете прав на это действие", " Du har inte behörighet att göra detta. "},
                    {"Элементы  успешно удалены", " Objekt har tagits bort "},
                    {"Элементы с указанным id не существует", " Elementen med det angivna id-numret finns inte "},
                    {"Элементы id которых меньше чем указанный и на которые у вас есть права  успешно удалены", " Element vars ID är mindre än den angivna och som du har rättigheter till har tagits bort "},
                    {"Элементы с указанным id не существует", " Elementen med det angivna id-numret finns inte "},
                    {"и на которые у вас есть права успешно удалены", " och som du har tagit bort rättigheter till "},
                    {"Элементы цена, которых меньше", " Varor med lägre pris "},
                    {"Колличество элементов в коллекции:", " Antalet objekt i samlingen: "},
                    {"Дата загрузки коллекции из файла:", " Datum för laddning av samlingen från filen: "},
                    {"Сохранения коллекции в файл в текущей сессии не происходило", " Samlingen sparades inte i en fil under den aktuella sessionen "},
                    {"Дата последнего сохранения коллекции в файл:", " Datum för senast sparade samling till fil: "},
                    {"Всего было введено валидных команд:", " Totalt anges giltiga kommandon: "},
                    {"штук", " bitar "},
                    {"Элемент успешно добавлен в коллекцию", " Objektet har lagts till i samlingen "},

                    {"Обьтекта с таким id не существует", " Ett objekt med detta ID finns inte "},
                    {"Значение строки не подходит", " Strängvärdet matchar inte "},
                    {"Значение поля было успешно обнавлено", " Fältvärdet uppdaterades framgångsrikt "},
                    {"Вы неверно выбрали поле для изменения", " Du har valt fel fält att ändra "},
                    {"У вас нет прав на изменения этого обьекта", " Du har inte behörighet att ändra detta objekt. "},
                    {"ключ может принимать только целое числовое значение", "nyckeln kan bara ta ett heltal numeriskt värde"},
                    {"ключ может принимать только целое числовое значение, строго больше нуля", " nyckeln kan bara ta ett heltal numeriskt värde, strikt större än noll "},
                    {"команды <", "kommandon <"},
                    {"> не существует", "> finns inte"},
                    {"Комманда", " Kommando "},
                    {"принемает", " tar "},
                    {"аргумента", " argument "},
            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
