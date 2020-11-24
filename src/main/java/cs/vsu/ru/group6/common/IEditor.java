package cs.vsu.ru.group6.common;

public interface IEditor<T> {
    void collectInfo();
    void apply(T ...newArgs);
}
