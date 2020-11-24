package cs.vsu.ru.group6.common;

import cs.vsu.ru.group6.Shapes.AbstractShape;
import javafx.scene.paint.Color;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DBManager {
    static final String JDBC_DRIVER = "org.h2.Driver";

    public DBManager() {
        createNewTable();
    }

    public List<AbstractShape> search(AbstractShape request) {
        List<AbstractShape> result = new ArrayList<>();
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select * from shapes where name like ?");
            //String sql = "select id, name, x1, y1, x2, y2, width, height, fillColor, borderColor from shapes where name like '" + name + "%'";
            statement.setString(1, "_" + request.getClass().getSimpleName().substring(1) + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Class<?> clz = Class.forName(request.getClass().getName());
                Constructor<?> constructor = clz.getConstructor(double.class, double.class, double.class, double.class);
                AbstractShape shape = (AbstractShape) constructor.newInstance(0, 0, 0, 0);

                shape.setId(rs.getLong(1));
                shape.setX0(rs.getDouble(3));
                shape.setY0(rs.getDouble(4));
                shape.setX1(rs.getDouble(5));
                shape.setY1(rs.getDouble(6));
                shape.setWidth(rs.getDouble(7));
                shape.setHeight(rs.getDouble(8));
                shape.setFillColor(Color.valueOf(rs.getString(9)));
                shape.setBorderColor(Color.valueOf(rs.getString(10)));
                result.add(shape);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void add(AbstractShape shape) {

        try (Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("insert into shapes (id, name, x1, y1, x2, y2, width, height, fillColor, borderColor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setLong(1, shape.getId());
            stmt.setString(2, shape.getClass().getSimpleName());
            stmt.setDouble(3, shape.getX0());
            stmt.setDouble(4, shape.getY0());
            stmt.setDouble(5, shape.getX1());
            stmt.setDouble(6, shape.getY1());
            stmt.setDouble(7, shape.getWidth());
            stmt.setDouble(8, shape.getHeight());
            stmt.setString(9, shape.getFillColor().toString());
            stmt.setString(10, shape.getBorderColor().toString());
            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    ("SET x1 = " + request.getX0() + ", ") +
                            "y1 = " + request.getY0() + ", " +
                            "x2 = " + request.getX1() + ", " +
                            "y2 = " + request.getY1() + ", " +
                            "width = " + request.getWidth() + ", " +
                            "height = " + request.getHeight() + ", " +
                            ("fillColor = \'%s\'," + request.getFillColor()) +
                            ("borderColor = \'%s\'" + request.getBorderColor()) +
                            "where id = " + request.getId() +
                            ("AND name = \'%s\'" + request.getClass().getSimpleName())
     */
    public void update(AbstractShape request) {
        try (Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    String.format(Locale.ENGLISH,
                        "update shapes set x1 = %f, y1 = %f, x2 = %f, y2 = %f, width = %f, height = %f, borderColor = \'%s\', fillColor = \'%s\' where id = %d and name = \'%s\'",
                            request.getX0(), request.getY0(), request.getX1(), request.getY1(), request.getWidth(), request.getHeight(), request.getBorderColor(), request.getFillColor(), request.getId(), request.getClass().getSimpleName()
                    )
            );
            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void remove(AbstractShape request) {
        try (Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "delete from shapes " +
                            "where id = " + request.getId() +
                            " AND name = '" + request.getClass().getSimpleName() + "'");
            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void createNewTable(){
        try (Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("DROP TABLE IF EXISTS SHAPES;");
            stmt.executeUpdate();
            PreparedStatement stmt1 = c.prepareStatement("create table Shapes (\n" +
                    "    id numeric not null primary key,\n" +
                    "    name varchar(15),\n" +
                    "    x1 numeric default 0,\n" +
                    "    y1 numeric default 0,\n" +
                    "    x2 numeric default 0,\n" +
                    "    y2 numeric default 0,\n" +
                    "    width numeric default 0,\n" +
                    "    height numeric default 0,\n" +
                    "    fillColor varchar(20) default \'FFFFFF\',\n" +
                    "    borderColor varchar(20)  default \'000000\'\n" +
                    ")");
            stmt1.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/shapes", "admin", "root");
    }
}