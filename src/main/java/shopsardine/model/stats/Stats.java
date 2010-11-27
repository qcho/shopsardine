package main.java.shopsardine.model.stats;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdesktop.application.ApplicationContext;

public class Stats {
	
	private final static String STATS_FILE = "stats.xml";
	
	private HashMap<String, Integer> stats;
	ApplicationContext context;

	public Stats(ApplicationContext context) {
		this.context = context;
		this.stats = loadStats();
	}
	
	public void incrementStat(String id) {
		if (stats.containsKey(id)){
			stats.put(id, (Integer)stats.get(id) + 1);
		} else {
			stats.put(id, 1);
		}
		System.out.println(id + " added to stats. " + stats.get(id));
		saveStats();
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Integer> loadStats(){
		try {
			return (HashMap<String, Integer>)context.getLocalStorage().load(STATS_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("creating new stats file. " + STATS_FILE);
		}
		return new HashMap<String, Integer>();
	};
	public void saveStats(){
		try {
			context.getLocalStorage().save(stats, STATS_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	public TableModel getTableModel(){
		return toTableModel(stats);
	}
	
	public static TableModel toTableModel(Map<String,Integer> map) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Product", "Count" }, 0
	    );
	    for (Map.Entry<String,Integer> entry : map.entrySet()) {
	        model.addRow(new Object[] { entry.getKey(), entry.getValue() });
	    }
	    return model;
	}


}
