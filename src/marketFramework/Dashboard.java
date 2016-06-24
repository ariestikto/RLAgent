package marketFramework;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import auctionSimulation.Auction;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import marketFramework.Time;
import userSimulation.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.TextArea;

public class Dashboard extends JFrame implements ActionListener {

	private int timeJump = 1;
	private User[] users = Snippet.createUsers();
	private User activeUser = users[0];
	private Auction a = new Auction();
	private Time t = new Time();
	//top pane information
	private JLabel labelDay;
	private JLabel labelWeather;
	
	//user pane information
	private JLabel userUID;
	private JLabel budgetInfo;
	private JLabel needsInfo;
	private JLabel currentelectricityinfo;
	private JLabel gainedElectricityInfo;
	private JLabel expenseInfo;
	private JLabel carTypeInfo;
	private JLabel carConsumptionInfo;
	private JLabel carCapacityInfo;
	
	//auction pane
	private TextArea auctionResult;
	
	//auction history pane 
	private TextArea auctionHistory;
	
	private JPanel contentPane;
	private JPanel timePanel;
	private JPanel userPanel;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Dashboard() {
		setTitle("Market Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		timePanel = new JPanel();
		contentPane.add(timePanel, BorderLayout.NORTH);
		topPane(timePanel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		userPanel = new JPanel();
		tabbedPane.addTab("Users", null, userPanel, null);
		userPane(userPanel);
		
		JPanel auctionPanel = new JPanel();
		tabbedPane.addTab("Auction", null, auctionPanel, null);
		auctionPane(auctionPanel);
		
		JPanel historyPanel = new JPanel();
		tabbedPane.addTab("History", null, historyPanel, null);
		historyPane(historyPanel);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		bottomPane(buttonPanel);
		
	}
	
	public void topPane(JPanel timePanel) {
		timePanel.setLayout(new BorderLayout(0, 0));
		
		labelDay = new JLabel("Day " +t.getDay()+ ", " +t.getDayName());
		timePanel.add(labelDay, BorderLayout.WEST);
		
		labelWeather = new JLabel("Weather: " + t.getWeatherName());
		timePanel.add(labelWeather, BorderLayout.EAST);
	}
	public void updateTopPane() {
		labelDay.setText("Day " +t.getDay()+ ", " +t.getDayName());
		labelWeather.setText("Weather: " + t.getWeatherName());
	}
	public void userPane(JPanel userPanel) {
		String[] UIDList = new String[users.length];
		for (int i = 0; i < UIDList.length; i++) {
			UIDList[i] = users[i].getUID();
		}
		
		GridBagLayout gbl_userPanel = new GridBagLayout();
		gbl_userPanel.columnWidths = new int[] {150, 100, 0, 0, 0, 0, 0, 0, 0, 0, 100};
		gbl_userPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_userPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_userPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		userPanel.setLayout(gbl_userPanel);
		
		JComboBox<String> UID = new JComboBox<>(UIDList);
		GridBagConstraints gbc_UID = new GridBagConstraints();
		gbc_UID.insets = new Insets(0, 0, 5, 5);
		gbc_UID.anchor = GridBagConstraints.NORTHWEST;
		gbc_UID.gridx = 1;
		gbc_UID.gridy = 1;
		userPanel.add(UID, gbc_UID);
		
		UID.addActionListener(new ActionListener() { 
        	@Override
            public void actionPerformed(ActionEvent e) { 
        		String s = (String) UID.getSelectedItem();//get the selected item
                for (int i = 0; i < users.length; i++) {
					if (users[i].getUID() == s) {
						activeUser = users[i];
						updateUserPane();
					}
				}
            } 
        });
		
		userUID = new JLabel("User " + activeUser.getUID());
		GridBagConstraints gbc_userUID = new GridBagConstraints();
		gbc_userUID.gridwidth = 2;
		gbc_userUID.insets = new Insets(0, 0, 5, 5);
		gbc_userUID.gridx = 0;
		gbc_userUID.gridy = 6;
		userPanel.add(userUID, gbc_userUID);
		
		JLabel lblBudget = new JLabel("Budget: ");
		lblBudget.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblBudget.setAlignmentX(Component.RIGHT_ALIGNMENT);
		GridBagConstraints gbc_lblBudget = new GridBagConstraints();
		gbc_lblBudget.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBudget.insets = new Insets(0, 0, 5, 5);
		gbc_lblBudget.gridx = 0;
		gbc_lblBudget.gridy = 8;
		userPanel.add(lblBudget, gbc_lblBudget);
		
		JLabel lblNeeds = new JLabel("Needs: ");
		lblNeeds.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_lblNeeds = new GridBagConstraints();
		gbc_lblNeeds.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNeeds.insets = new Insets(0, 0, 5, 5);
		gbc_lblNeeds.gridx = 0;
		gbc_lblNeeds.gridy = 10;
		userPanel.add(lblNeeds, gbc_lblNeeds);
		
		JLabel lblCurrentElectricity = new JLabel("Current Electricity: ");
		lblCurrentElectricity.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_lblCurrentElectricity = new GridBagConstraints();
		gbc_lblCurrentElectricity.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCurrentElectricity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentElectricity.gridx = 0;
		gbc_lblCurrentElectricity.gridy = 12;
		userPanel.add(lblCurrentElectricity, gbc_lblCurrentElectricity);
		
		JLabel lblGainedElectricity = new JLabel("Gained Electricity: ");
		lblGainedElectricity.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_lblGainedElectricity = new GridBagConstraints();
		gbc_lblGainedElectricity.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGainedElectricity.insets = new Insets(0, 0, 5, 5);
		gbc_lblGainedElectricity.gridx = 0;
		gbc_lblGainedElectricity.gridy = 14;
		userPanel.add(lblGainedElectricity, gbc_lblGainedElectricity);
		
		JLabel lblExpenses = new JLabel("Expenses: ");
		lblExpenses.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_lblExpenses = new GridBagConstraints();
		gbc_lblExpenses.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblExpenses.insets = new Insets(0, 0, 5, 5);
		gbc_lblExpenses.gridx = 0;
		gbc_lblExpenses.gridy = 16;
		userPanel.add(lblExpenses, gbc_lblExpenses);
		
		budgetInfo = new JLabel(activeUser.getBudget() + " p");
		budgetInfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_budgetInfo = new GridBagConstraints();
		gbc_budgetInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_budgetInfo.insets = new Insets(0, 0, 5, 5);
		gbc_budgetInfo.gridx = 1;
		gbc_budgetInfo.gridy = 8;
		userPanel.add(budgetInfo, gbc_budgetInfo);
		
		needsInfo = new JLabel(activeUser.getDailyNeeds() + " kWh");
		needsInfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_needsInfo = new GridBagConstraints();
		gbc_needsInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_needsInfo.insets = new Insets(0, 0, 5, 5);
		gbc_needsInfo.gridx = 1;
		gbc_needsInfo.gridy = 10;
		userPanel.add(needsInfo, gbc_needsInfo);
		
		currentelectricityinfo = new JLabel(activeUser.getCurrentElectricity() + " kWh");
		currentelectricityinfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_currentelectricityinfo = new GridBagConstraints();
		gbc_currentelectricityinfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_currentelectricityinfo.insets = new Insets(0, 0, 5, 5);
		gbc_currentelectricityinfo.gridx = 1;
		gbc_currentelectricityinfo.gridy = 12;
		userPanel.add(currentelectricityinfo, gbc_currentelectricityinfo);
		
		gainedElectricityInfo = new JLabel(activeUser.gainedElectricity() + " kWh");
		gainedElectricityInfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_gainedElectricityInfo = new GridBagConstraints();
		gbc_gainedElectricityInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_gainedElectricityInfo.insets = new Insets(0, 0, 5, 5);
		gbc_gainedElectricityInfo.gridx = 1;
		gbc_gainedElectricityInfo.gridy = 14;
		userPanel.add(gainedElectricityInfo, gbc_gainedElectricityInfo);
		
		expenseInfo = new JLabel(activeUser.getExpenses() + " p");
		expenseInfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_expenseInfo = new GridBagConstraints();
		gbc_expenseInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_expenseInfo.insets = new Insets(0, 0, 5, 5);
		gbc_expenseInfo.gridx = 1;
		gbc_expenseInfo.gridy = 16;
		userPanel.add(expenseInfo, gbc_expenseInfo);
		
		JLabel lblCar = new JLabel("Car Information");
		GridBagConstraints gbc_lblCar = new GridBagConstraints();
		gbc_lblCar.gridwidth = 4;
		gbc_lblCar.insets = new Insets(0, 0, 5, 0);
		gbc_lblCar.gridx = 6;
		gbc_lblCar.gridy = 6;
		userPanel.add(lblCar, gbc_lblCar);
		
		JLabel lblCarType = new JLabel("Car Type: ");
		lblCarType.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_lblCarType = new GridBagConstraints();
		gbc_lblCarType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCarType.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarType.gridx = 6;
		gbc_lblCarType.gridy = 8;
		userPanel.add(lblCarType, gbc_lblCarType);
		
		JLabel lblBatteryConsumption = new JLabel("Battery Consumption: ");
		lblBatteryConsumption.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_lblBatteryConsumption = new GridBagConstraints();
		gbc_lblBatteryConsumption.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBatteryConsumption.insets = new Insets(0, 0, 5, 5);
		gbc_lblBatteryConsumption.gridx = 6;
		gbc_lblBatteryConsumption.gridy = 10;
		userPanel.add(lblBatteryConsumption, gbc_lblBatteryConsumption);
		
		JLabel lblBatteryCapacity = new JLabel("Battery Capacity: ");
		lblBatteryCapacity.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_lblBatteryCapacity = new GridBagConstraints();
		gbc_lblBatteryCapacity.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBatteryCapacity.insets = new Insets(0, 0, 5, 5);
		gbc_lblBatteryCapacity.gridx = 6;
		gbc_lblBatteryCapacity.gridy = 12;
		userPanel.add(lblBatteryCapacity, gbc_lblBatteryCapacity);
		
		carTypeInfo = new JLabel(activeUser.getCar().getCarName());
		carTypeInfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_carTypeInfo = new GridBagConstraints();
		gbc_carTypeInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_carTypeInfo.insets = new Insets(0, 0, 5, 5);
		gbc_carTypeInfo.gridx = 7;
		gbc_carTypeInfo.gridy = 8;
		userPanel.add(carTypeInfo, gbc_carTypeInfo);
		
		carConsumptionInfo = new JLabel(activeUser.getCar().getConsumption() + " kWh/km");
		carConsumptionInfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_carConsumptionInfo = new GridBagConstraints();
		gbc_carConsumptionInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_carConsumptionInfo.insets = new Insets(0, 0, 5, 5);
		gbc_carConsumptionInfo.gridx = 7;
		gbc_carConsumptionInfo.gridy = 10;
		userPanel.add(carConsumptionInfo, gbc_carConsumptionInfo);
		
		carCapacityInfo = new JLabel(activeUser.getCar().getBatteryCapacity() + " kWh");
		carCapacityInfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_carCapacityInfo = new GridBagConstraints();
		gbc_carCapacityInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_carCapacityInfo.insets = new Insets(0, 0, 5, 5);
		gbc_carCapacityInfo.gridx = 7;
		gbc_carCapacityInfo.gridy = 12;
		userPanel.add(carCapacityInfo, gbc_carCapacityInfo);
	}
	public void auctionPane(JPanel auctionPanel) {
		auctionPanel.setLayout(new BorderLayout(0, 0));
		auctionResult = new TextArea();
		auctionPanel.add(auctionResult);
		auctionResult.setEditable(false);
	}
	public void historyPane(JPanel historyPanel) {
		historyPanel.setLayout(new BorderLayout(0, 0));
		auctionHistory = new TextArea();
		historyPanel.add(auctionHistory);
		auctionHistory.setEditable(false);
	}
	public void updateUserPane() {
		userUID.setText("User " + activeUser.getUID());
		budgetInfo.setText(activeUser.getBudget() + " p");
		needsInfo.setText(activeUser.getDailyNeeds() + " kWh");
		currentelectricityinfo.setText(activeUser.getCurrentElectricity() + " kWh");
		gainedElectricityInfo.setText(activeUser.gainedElectricity() + " kWh");
		expenseInfo.setText(activeUser.getExpenses() + " p");
		carTypeInfo.setText(activeUser.getCar().getCarName());
		carConsumptionInfo.setText(activeUser.getCar().getConsumption() + " kWh/km");
		carCapacityInfo.setText(activeUser.getCar().getBatteryCapacity() + " kWh");
	}
	public void bottomPane(JPanel buttonPanel) {
		String[] timeMenu = {"1 Day", "3 Days", "7 Days", "30 Days", "100 Days"};
		
		JComboBox<String> timeChoice = new JComboBox<>(timeMenu);
		buttonPanel.add(timeChoice);
		
		JButton btnAdvanceTime = new JButton("Advance Time");
		buttonPanel.add(btnAdvanceTime);
		
		timeChoice.addActionListener(new ActionListener() { 
        	@Override
            public void actionPerformed(ActionEvent e) { 
        		String s = (String) timeChoice.getSelectedItem();//get the selected item
                switch (s) {
					case "1 Day":
						timeJump = 1;
						break;
					case "3 Days":
						timeJump = 3;
						break;
					case "7 Days":
						timeJump = 7;
						break;
					case "30 Days":
						timeJump = 30;
						break;
					case "100 Days":
						timeJump = 100;
						break;

				}
            } 
        });
		
		btnAdvanceTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < timeJump; i++) {
					Snippet.endOfDay(users);
					t.advanceTime();
					Snippet.startOfDay(users, t);
					a.runAuction(users, t, auctionResult, auctionHistory);
					updateTopPane();
					updateUserPane();
					
				}
			}
		});
	}
	public void actionPerformed(ActionEvent e) {}
}
