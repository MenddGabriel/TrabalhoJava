package tools.paineis;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Interface funcional que unifica os três métodos do DocumentListener,
 * permitindo uso com lambda expressions.
 */
@FunctionalInterface
public interface DocumentAdapter extends DocumentListener {
    void update(DocumentEvent e);

    @Override default void insertUpdate(DocumentEvent e)  { update(e); }
    @Override default void removeUpdate(DocumentEvent e)  { update(e); }
    @Override default void changedUpdate(DocumentEvent e) { update(e); }
}
