package com.users;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DButils {
	public static TableModel resultsetToTableModel(ResultSet rs) {
		try {
			ResultSetMetaData metadata=rs.getMetaData();
			int numberOfColumns=metadata.getColumnCount();
			Vector columnNames=new Vector();
			
			for(int column=0;column<numberOfColumns;column++) {
				columnNames.addElement(metadata.getColumnLabel(column +1));
				
			}
			
			Vector rows=new Vector();
			while(rs.next()) {
				Vector newrow=new Vector();
				
				for(int i=1;i<=numberOfColumns;i++) {
				newrow.addElement(rs.getObject(i));	
				}
				rows.addElement(newrow);
				
			}
			
			return new DefaultTableModel(rows,columnNames);
			
		}catch(Exception e) {
			
		}
	
		return null;
		}

}
