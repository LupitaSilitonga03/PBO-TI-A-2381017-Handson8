package views;

import repositories.TodoListRepository;
import repositories.TodoListRepositorylmpl;
import services.TodoListService;
import services.TodoListServicelmpl;

public class main {
    public static void main(String[] args) {
        TodoListRepository todoListRepository = new TodoListRepositorylmpl();
        TodoListService todoListService = new TodoListServicelmpl(todoListRepository);
        TodoListView todoListView = new TodoListTerminalView(todoListService);
        todoListView.run();

    }
}
