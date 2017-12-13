/**
 * Project: DataMiner
 * File: Driver.java
 * Date: Nov 28, 2017
 * Time: 7:54:14 PM
 */
package main;

import java.awt.EventQueue;

import main.ui.MainFrame;

/**
 * @author Jason Keum (QCFB QuanchiFootball) https://github.com/quanchifootball
 * 
 *         DATA MINER
 * 
 *         This program takes in an xlsx files with specified fields and creates
 *         an easy to use interface for mining contact data on the internet.
 *         Nothing fancy here, just a set of simple features that would
 *         otherwise take up time for for the average data entry activity. - The
 *         contact dialog always stays on top, making it easier to copy and
 *         paste from browser. - The combo box will populate with the most
 *         commonly used corporate email formats. - Easily cycle through
 *         contacts. Contact data is saved whenever the user clicks Next or
 *         Previous. - Standard load, refresh and save as functions.
 */
public class Driver {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
