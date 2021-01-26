В этот раз Вам предстоит самостоятельно написать алгоритм, который строит все возможные финальные состояния досок упрощенной версии игры Го.

## Правила упрощенной игры Го
На случай, если Вы не знакомы с правилами полной версии, с ними можно ознакомится вот тут. Упрощенная версия игры имеет следующие ограничения:

- поле очень маленькое, всего 3 на 3;
- игра продолжается до тех пор, пока все поле не будет заполнено;
- если на клетке установлена фигура белого или черного цвета, то ее менять нельзя;
- Фактически в данной версии у Вас есть поле 3 на 3 и каждый игрок за свой ход выбирает пустую клетку и ставит туда фигурку своего цвета. Игра продолжается до тех пор, пока есть хоть одна пустая клетка. Игра соответственно продолжается ровно 9 ходов. В конце на поле будет 5 фигурок игрока номер один и 4 игрока номер два.

### Задача
Задача написать логику, которая бы создавала все возможные доски окончания игры.

По факту, это все варианты досок на которых 5 чёрных фигурок (так как они ходят первыми, согласно правилам) и 4 белых. Само собой, это достаточно несложная задача, однако мы бы просили Вас рассматривать ее как задачу обхода графа. Подобно той что мы с Вами рассматривали на занятии.

### За Вас уже реализованы:

- GoField - класс, который описывает доску. Обратите внимание, что его поля имеют область доступа public - это сделано намеренно для упрощения. Также, возможно, вам захочется дописать в нем еще и свой конструктор, но это не обязательно;
- Figure - enum, который описывает фигурки игроков;
- GraphBuilder - реализация Callable, которая похожа на ту, что мы рассматривали на уроке, однако она должна возвращать Set из всех конечных состояний доски;

Почему в GraphBuilder так много //BEGIN //END:

Решение разбито на несколько блоков //BEGIN //END, несмотря на то, что это цельное тело одного метода call(). Это сделано с целью отображения решения учителя частями, а не сразу большим куском кода. Проще игнорировать (удалить) все //BEGIN //END в классе GraphBuilder, кроме //BEGIN #1 & //END #4.