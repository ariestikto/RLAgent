package marketFramework;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import agent.learningAgent.Action;
import agent.learningAgent.Reward;
import agent.learningAgent.State;
import auctionSimulation.Auction;
import userSimulation.User;

public class Dashboard extends JFrame implements ActionListener {

	private int timeJump = 1;
	private User[] users = Snippet.createUsers();
	private User activeUser = users[0];
	private User agent = users[0];
	private Auction a = new Auction();
	private Time t = new Time();
	
	private JPanel contentPane;
	private JPanel timePanel;
	private JPanel userPanel;
	
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
	
	//agent pane
	private JLabel lblUserUid;
	private JLabel lblUidval;
	private JLabel lblState;
	private JLabel lblDay;
	private JLabel lblDayvalue;
	private JLabel lblWeather;
	private JLabel lblWeathervalue;
	private JLabel lblElectricityLevel;
	private JLabel lblElectricitylevelvalue;
	private JLabel lblAction;
	private JLabel lblAddedElectricity;
	private JLabel lblAddedelectricityvalue;
	private JLabel lblBudgetLevel;
	private JLabel lblBudgetlevelvalue;
	private JLabel lblNextstate;
	private JLabel lblDay_1;
	private JLabel lblNextdayvalue;
	private JLabel lblElectricityLevel_1;
	private JLabel lblNextelectricitylevelvalue;
	private JLabel lblRewardSignal;
	private JLabel lblRewardvalue;
	private JLabel lblQValue;
	private JLabel lblQvalue;
	
	
	
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
		
		JPanel agentPanel = new JPanel();
		tabbedPane.addTab("Agent", null, agentPanel, null);
		agentPane(agentPanel);
		
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
			if (users[i].getStrategy() == 5) {
				agent = users[i];
			}
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
		gbc_lblBudget.gridy = 10;
		userPanel.add(lblBudget, gbc_lblBudget);
		
		JLabel lblNeeds = new JLabel("Needs: ");
		lblNeeds.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_lblNeeds = new GridBagConstraints();
		gbc_lblNeeds.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNeeds.insets = new Insets(0, 0, 5, 5);
		gbc_lblNeeds.gridx = 0;
		gbc_lblNeeds.gridy = 8;
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
		gbc_budgetInfo.gridy = 10;
		userPanel.add(budgetInfo, gbc_budgetInfo);
		
		needsInfo = new JLabel(activeUser.getDailyNeeds() + " kWh");
		needsInfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_needsInfo = new GridBagConstraints();
		gbc_needsInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_needsInfo.insets = new Insets(0, 0, 5, 5);
		gbc_needsInfo.gridx = 1;
		gbc_needsInfo.gridy = 8;
		userPanel.add(needsInfo, gbc_needsInfo);
		
		currentelectricityinfo = new JLabel(activeUser.getCurrentElectricity() + " kWh");
		currentelectricityinfo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints gbc_currentelectricityinfo = new GridBagConstraints();
		gbc_currentelectricityinfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_currentelectricityinfo.insets = new Insets(0, 0, 5, 5);
		gbc_currentelectricityinfo.gridx = 1;
		gbc_currentelectricityinfo.gridy = 12;
		userPanel.add(currentelectricityinfo, gbc_currentelectricityinfo);
		
		gainedElectricityInfo = new JLabel(activeUser.clinchedElectricity() + " kWh");
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
	public void agentPane(JPanel agentPanel) {
		agentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(61dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(74dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(65dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(92dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(23dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(17dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(17dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(17dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(17dlu;default)"),}));
		
		lblUserUid = new JLabel("User UID:");
		agentPanel.add(lblUserUid, "2, 2, right, default");
		
		lblUidval = new JLabel(agent.getUID());
		agentPanel.add(lblUidval, "4, 2");
		
		lblState = new JLabel("State");
		agentPanel.add(lblState, "2, 6, 3, 1, center, default");
		
		lblAction = new JLabel("Action");
		agentPanel.add(lblAction, "8, 6, 3, 1, center, default");
		
		lblNextstate = new JLabel("NextState");
		agentPanel.add(lblNextstate, "14, 6, 3, 1, center, default");
		
		lblDay = new JLabel("Day:");
		agentPanel.add(lblDay, "2, 8, right, default");
		
		lblDayvalue = new JLabel(t.getDayName());
		agentPanel.add(lblDayvalue, "4, 8");
		
		lblAddedElectricity = new JLabel("Added Electricity:");
		agentPanel.add(lblAddedElectricity, "8, 8, right, default");
		
		lblAddedelectricityvalue = new JLabel(agent.getAgent().getLastStateAction().getAction().getAddedAmountLevel()*agent.getCar().getBatteryCapacity() + "");
		agentPanel.add(lblAddedelectricityvalue, "10, 8, left, default");
		
		lblDay_1 = new JLabel("Day:");
		agentPanel.add(lblDay_1, "14, 8, right, default");
		
		lblNextdayvalue = new JLabel(t.getNextDayName());
		agentPanel.add(lblNextdayvalue, "16, 8");
		
		lblWeather = new JLabel("Weather:");
		agentPanel.add(lblWeather, "2, 10, right, default");
		
		lblWeathervalue = new JLabel(t.getWeatherName());
		agentPanel.add(lblWeathervalue, "4, 10");
		
		lblBudgetLevel = new JLabel("Budget Level:");
		agentPanel.add(lblBudgetLevel, "8, 10, right, default");
		
		lblBudgetlevelvalue = new JLabel("BudgetLevelValue");
		agentPanel.add(lblBudgetlevelvalue, "10, 10");
		
		lblElectricityLevel_1 = new JLabel(agent.getAgent().getLastStateAction().getAction().getBudget());
		agentPanel.add(lblElectricityLevel_1, "14, 10, right, default");
		
		lblNextelectricitylevelvalue = new JLabel(agent.getAgent().getLastStateAction().getState().nextState(agent.getAgent().getLastStateAction().getAction(), agent).getElectricityLevel()*agent.getCar().getBatteryCapacity() + "");
		agentPanel.add(lblNextelectricitylevelvalue, "16, 10");
		
		lblElectricityLevel = new JLabel("Electricity Level:");
		agentPanel.add(lblElectricityLevel, "2, 12, right, default");
		
		lblElectricitylevelvalue = new JLabel(agent.getAgent().getLastStateAction().getState().getElectricityLevel() + "");
		agentPanel.add(lblElectricitylevelvalue, "4, 12");
		
		lblRewardSignal = new JLabel("Reward Signal:");
		agentPanel.add(lblRewardSignal, "2, 18, right, default");
		
		lblRewardvalue = new JLabel(Reward.RewardPatternA(agent) + "");
		agentPanel.add(lblRewardvalue, "4, 18");
		
		lblQValue = new JLabel("Q Value:");
		agentPanel.add(lblQValue, "2, 20, right, default");
		
		lblQvalue = new JLabel(agent.getAgent().findSAPair(agent.getAgent().getLastStateAction().getState(), agent.getAgent().getLastStateAction().getAction()) + "");
		agentPanel.add(lblQvalue, "4, 20");
	}
	public void updateUserPane() {
		userUID.setText("User " + activeUser.getUID());
		budgetInfo.setText(activeUser.getBudget() + " p");
		needsInfo.setText(activeUser.getDailyNeeds() + " kWh");
		currentelectricityinfo.setText(activeUser.getCurrentElectricity() + " kWh");
		gainedElectricityInfo.setText(activeUser.clinchedElectricity() + " kWh");
		expenseInfo.setText(activeUser.getExpenses() + " p");
		
		carTypeInfo.setText(activeUser.getCar().getCarName());
		carConsumptionInfo.setText(activeUser.getCar().getConsumption() + " kWh/km");
		carCapacityInfo.setText(activeUser.getCar().getBatteryCapacity() + " kWh");
	}
	public void updateAgentPane(double lastNeeds, double lastBudget, State lastState, Action lastAction, double lastSpending) {

	}
	public void bottomPane(JPanel buttonPanel) {
		String[] timeMenu = {"1 Day", "3 Days", "7 Days", "30 Days", "100 Days"};
		
		JComboBox<String> timeChoice = new JComboBox<>(timeMenu);
		buttonPanel.add(timeChoice);
		
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
		
		JButton btnAdvanceTime = new JButton("Advance Time");
		buttonPanel.add(btnAdvanceTime);
		
		btnAdvanceTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < timeJump; i++) {
					t.advanceTime();
					Snippet.startOfDay(users, t);
					a.runAuction(users, t, auctionResult, auctionHistory);
					Snippet.endOfDay(users);
					updateTopPane();
					updateUserPane();
				}
			}
		});
	}
	public void actionPerformed(ActionEvent e) {}
}
