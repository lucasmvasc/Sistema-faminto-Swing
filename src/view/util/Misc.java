package view.util;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Misc {
	public static final String[] labelListCliente = { "Fazer Pedido", "Dados cadastrais", "Cancelar pedido",
			"Alterar pedido" };
	public static final String[] labelListFuncionario = { "Ver pedidos do dia", "Listar entregadores", "Itens vendidos",
			"Reativar clientes" };
	public static Long code = 1l;
	public static final Font FONT = new Font("SansSerif", Font.BOLD, 16);

	public static <T> Field[] getChildAndSuperFields(T n) {
		Field[] fds_super = n.getClass().getSuperclass().getDeclaredFields();
		Field[] fds_child = n.getClass().getDeclaredFields();
		return acessibleField(fds_super, fds_child);
	}

	public static <T> String[] getChildAndSuperStrings(T n) {
		Field[] fields = Misc.getChildAndSuperFields(n);
		String[] o = new String[fields.length];
		try {
			for (int i = 0; i < fields.length; i++)
				o[i] = fields[i].getName();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return o;
	}

	private static <T> Field getFieldPosition(T n, int column) {
		return Misc.getChildAndSuperFields(n)[column];
	}

	public static <T> Object getFieldPositionObject(T n, int column) {
		Object o = null;
		try {
			o = Misc.getFieldPosition(n, column).get(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static <T> String getFieldPositionColumnName(T n, int column) {
		String o = "Fail";
		try {
			o = Misc.getFieldPosition(n, column).getName();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return o;
	}

	private static Field[] acessibleField(Field[] first, Field[] second) {
		Field[] result = new Field[first.length + second.length];
		for (int i = 0; i < first.length; i++) {
			result[i] = first[i];
			result[i].setAccessible(true);
		}
		for (int i = 0; i < second.length; i++) {
			result[i + first.length] = second[i];
			result[i].setAccessible(true);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] concatTwoArrays(T n, T[] first, T[] second) {
		T[] result = (T[]) Array.newInstance(n.getClass(), first.length + second.length);
		for (int i = 0; i < first.length; i++)
			result[i] = first[i];
		for (int i = 0; i < second.length; i++)
			result[i + first.length] = second[i];
		return result;
	}

	public static void setFont(Component... list) {
		for (Component c : list)
			c.setFont(Misc.FONT);
	}

	public static Dimension getScreenDimension() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
}
