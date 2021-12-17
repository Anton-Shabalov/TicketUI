package resurses;

import java.util.ListResourceBundle;

public class Resources_es_EC extends ListResourceBundle {

    private String name = "Spanish";

    private static final Object[][] contents =
            {
                    {"Билет", " Billete "},
                    {"id:", " número "},
                    {"Билет на:", " Entrada para: "},
                    {"Место:", " Un lugar: "},
                    {"Дата создания:", " Fecha de creación: "},
                    {"Цена:", " Precio: "},
                    {"Скидка:", " Descuento: "},
                    {"Тип билета:", " Tipo de entrada: "},
                    {"Место проведения:", " Localización: "},
                    {"Ряд:", " Fila: "},
                    {"Название заведения:", " Nombre de la instalación: "},
                    {"Количество мест всего:", " Cantidad de asientos en total: "},
                    {"Вид заведения", " Tipo de establecimiento "},
                    {"Адрес:", " Habla a: "},
                    {"индекс:", " índice: "},
                    {"Расположение", " Localización: "},
                    {"Кордината по x:=", " Coordenada X: = "},
                    {"Кордината по y=", " Coordenada Y = "},
                    {"Кордината по z=", " Coordinar por z = "},
                    {"help                        : вывести справку по доступным командам", "help                        : muestra ayuda para los comandos disponibles"},
                    {"info                        : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", "info                        : imprime información sobre la colección (tipo, fecha de inicialización, número de elementos, etc.) en la salida estándar"},
                    {"show                        : вывести в стандартный поток вывода все элементы коллекции в строковом представлении", "show                        : imprime en la salida estándar todos los elementos de la colección en representación de cadena"},
                    {"info                        : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", "info                        : imprime información sobre la colección (tipo, fecha de inicialización, número de elementos, etc.) en la salida estándar"},
                    {"show                        : вывести в стандартный поток вывода все элементы коллекции в строковом представлении", "show                        : imprime en la salida estándar todos los elementos de la colección en representación de cadena"},
                    {"insert id                   : добавить новый элемент с заданным ключом", "insert id                   : agrega un nuevo elemento con la clave dada"},
                    {"update id                   : обновить значение элемента коллекции, id которого равен заданному", "update id                   : actualiza el valor del elemento de la colección cuyo id es igual al dado"},
                    {"remove_key id               : удалить элемент из коллекции по его ключу", "remove_key id               : eliminar un elemento de la colección por su clave"},
                    {"clear                       : очистить коллекцию", "clear                       : colección clara"},
                    {"execute_script file_name    : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.", "execute_script file_name    : lee y ejecuta el script desde el archivo especificado. El script contiene comandos en la misma forma en que el usuario los ingresa de forma interactiva."},
                    {"exit                        : завершить программу (без сохранения в файл)", "exit                        : finaliza el programa (sin guardar en archivo)"},
                    {"remove_lower id             : удалить из коллекции все элементы, меньшие, чем заданный", "remove_lower id             : elimina todos los elementos de la colección que sean menores que el dado"},
                    {"history                     : вывести последние 15 команд (без их аргументов)", "history                     : imprime los últimos 15 comandos (sin sus argumentos)"},
                    {"remove_lower_key id         : удалить из коллекции все элементы, ключ которых меньше, чем заданный", "remove_lower_key id         : eliminar de la colección todos los elementos cuya clave sea menor que la dada"},
                    {"sum_of_discount             : вывести сумму значений поля discount для всех элементов коллекции", "sum_of_discount             : muestra la suma de los valores del campo de descuento para todos los elementos de la colección"},
                    {"filter_contains_name name   : вывести элементы, значение поля name которых содержит заданную подстроку", "filter_contains_name name   : muestra elementos cuyo valor de campo de nombre contiene la subcadena dada"},
                    {"print_field_descending_type : вывести значения поля type всех элементов в порядке убывания", "print_field_descending_type : imprime los valores del campo de tipo de todos los elementos en orden descendente"},
                    {"Вы не имеете прав на это действие", " No está autorizado para hacer esto. "},
                    {"Элементы  успешно удалены", " Elementos eliminados correctamente "},
                    {"Элементы с указанным id не существует", " Los elementos con la identificación especificada no existen "},
                    {"Элементы id которых меньше чем указанный и на которые у вас есть права  успешно удалены", " Los elementos cuya identificación es menor que la especificada y sobre los que tiene derechos se han eliminado con éxito "},
                    {"Элементы с указанным id не существует", " Los elementos con la identificación especificada no existen "},
                    {"и на которые у вас есть права успешно удалены", " y a los que tiene derechos eliminados con éxito "},
                    {"Элементы цена, которых меньше", " Artículos con menor precio "},
                    {"Колличество элементов в коллекции:", " El número de elementos de la colección: "},
                    {"Дата загрузки коллекции из файла:", " Fecha de carga de la colección desde archivo: "},
                    {"Сохранения коллекции в файл в текущей сессии не происходило", " La colección no se guardó en un archivo en la sesión actual "},
                    {"Дата последнего сохранения коллекции в файл:", " Fecha del último guardado de la colección en archivo: "},
                    {"Всего было введено валидных команд:", " En total, se ingresaron comandos válidos: "},
                    {"штук", "piezas"},
                    {"Элемент успешно добавлен в коллекцию", " El artículo se agregó correctamente a la colección. "},
                    {"Элемент успешно добавлен в коллекцию", " El artículo se agregó correctamente a la colección. "},
                    {"Обьтекта с таким id не существует", " Un objeto con esta identificación no existe "},
                    {"Значение строки не подходит", " El valor de la cadena no coincide "},
                    {"Значение поля было успешно обнавлено", " El valor del campo se actualizó correctamente "},
                    {"Вы неверно выбрали поле для изменения", " Ha seleccionado el campo incorrecto para cambiar "},
                    {"У вас нет прав на изменения этого обьекта", " No tienes permiso para modificar este objeto. "},
                    {"ключ может принимать только целое числовое значение", " la clave solo puede tomar un valor numérico entero "},
                    {"ключ может принимать только целое числовое значение, строго больше нуля", " la clave solo puede tomar un valor numérico entero, estrictamente mayor que cero "},
                    {"команды <", " comandos < "},
                    {"> не существует", " > no existe "},
                    {"Комманда", " Mando "},
                    {"принемает", " acepta "},
                    {"аргумента", " argumento "},

            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }


}
