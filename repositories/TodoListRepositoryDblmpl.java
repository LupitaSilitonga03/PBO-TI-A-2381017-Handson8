package repositories;

import config.Database;
import entities.TodoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TodoListRepositoryDblmpl implements TodoListRepository {
    private final Database database;

    public TodoListRepositoryDblmpl(final Database database) {
        this.database = database;
    }

    @Override
    public TodoList[] getAll() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM todos";
        ArrayList<TodoList> todoLists = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoList todoList = new TodoList();
                Integer id = resultSet.getInt(1);
                String todo = resultSet.getString(2);
                todoList.setId(id);
                todoList.setTodo(todo);
                todoLists.add(todoList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return todoLists.toArray(new TodoList[0]);
    }

    @Override
    public void add(TodoList todoList) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO todos(todo) VALUES(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, todoList.getTodo());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean remove(Integer id) {
        String sqlStatement = "DELETE FROM todos WHERE id = ?";
        Connection connection = database.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Delete Successful!");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean edit(TodoList todoList) {
        return null;
    }
}