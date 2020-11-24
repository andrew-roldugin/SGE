package cs.vsu.ru.group6.common;

import cs.vsu.ru.group6.Shapes.AbstractShape;
import cs.vsu.ru.group6.Shapes.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<EventType, List<PaintListener>> listeners = new HashMap<>();

    public EventManager(EventType ...operations) {
        for (EventType operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(AbstractShape target, PaintListener listener) {
        List<PaintListener> sbscr = listeners.get(target);
        sbscr.add(listener);
    }

    public void unsubscribe(EventType eventType, PaintListener listener) {
        List<PaintListener> sbscr = listeners.get(eventType);
        sbscr.remove(listener);
    }

    public void notify(EventType eventType, Drawable target) {
        List<PaintListener> sbscr = listeners.get(eventType);
        for (PaintListener listener : sbscr) {
            listener.paint();
        }
    }
}
