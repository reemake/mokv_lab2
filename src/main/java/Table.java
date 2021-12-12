import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Table extends JFrame {

    private String[] col;
    private String[][] data;

    public Table(String[] col, String[][] data) {
        super("Суммирование большого количества слагаемых");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable table = new JTable(data, col);
        table.getTableHeader().setFont(new Font("Serif", Font.PLAIN, 22));
        table.setFont(new Font("Serif", Font.PLAIN, 22));
        table.setRowHeight(table.getRowHeight() + 10);
        table.setRowMargin(table.getRowMargin() + 5);

        // Настройка заголовков таблицы
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        // Подключение заголовка таблицыHeaderRenderer
        header.setDefaultRenderer(new HeaderRenderer());
        // Размещение таблицы в панели прокрутки
        getContentPane().add(new JScrollPane(table));

        JFrame f = new JFrame();
        f.setSize(1200, 700);
        f.add(new JScrollPane(table));
        f.setVisible(true);
    }

    class HeaderRenderer extends DefaultTableCellRenderer
    {
        // Границы заголовков колонок
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);

        // Метод возвращает визуальный компонент для прорисовки
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column)
        {
            // Надпись из базового класса
            JLabel label = (JLabel)super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            // Выравнивание строки заголовка
            label.setHorizontalAlignment(SwingConstants.CENTER);
            // Настройка цвет фона метки
            float[] hsb = Color.RGBtoHSB(224, 224, 224, null);
            label.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));

            label.setBorder(border);
            return label;
        }
    }
}
