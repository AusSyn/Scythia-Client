package com.client;

import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;

import javax.imageio.ImageIO;

import com.client.client.GameClient;
import com.client.client.ResourceLoader;
import com.client.client.signlink;

import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class Jframe extends GameClient implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;

	public Jframe(String[] args, int width, int height, boolean resizable) {
		super();
		try {
			signlink.startpriv(InetAddress.getByName(Configuration.HOST));
			initUI(width, height, resizable);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void initUI(int width, int height, boolean resizable) {
		try {
			try
		      {
				UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceNebulaLookAndFeel");
		        JFrame.setDefaultLookAndFeelDecorated(true);
		        JDialog.setDefaultLookAndFeelDecorated(true);
		      }
		      catch (ClassNotFoundException e)
		      {
		        System.out.println("Theme not detected, reverting to OS Default.");
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		      }
			
			frame = new JFrame(Configuration.CLIENT_NAME + " ");
			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new WindowAdapter() {
				
		    @Override
		    public void windowClosing(WindowEvent we) { 
		        String options[] = {"Yes", "No"};
		        int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "Solara",
		        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options , options[1]);
		       if(userPrompt == JOptionPane.YES_OPTION) {
		        	if(!Configuration.DEVELOPMENT_SERVER) {
						//openURL("http://Velkora.org/forums");
					}
					System.exit(-1);
		            System.exit(0);
		        } else {
		        	 
		        }
		    }
		});
			setFocusTraversalKeysEnabled(false);
			JPanel gamePanel = new JPanel();
			Insets insets = this.getInsets();
			if(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
				width += 10;
				height += 10;
			}
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int w = 765;
			int h = 503;
			int x = (dim.width-w)/2;
			int y = (dim.height-h)/2;
			frame.setLocation(x, y);
			gamePanel.setPreferredSize(new Dimension(765, 503));
			frame.setLayout(new BorderLayout());
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setBackground(Color.BLACK);
			URL icon64 = Jframe.class.getResource("/com/client/client/images/icon1.png");
			URL icon32 = Jframe.class.getResource("/com/client/client/images/icon2.png");
			URL icon16 = Jframe.class.getResource("/com/client/client/images/icon3.png");
			try {
				Image whip64 = ImageIO.read(icon64.openStream());
				Image whip32 = ImageIO.read(icon32.openStream());
				Image whip16 = ImageIO.read(icon16.openStream());
				ArrayList<Image> icons = new ArrayList<Image>();
			//	icons.add(whip64);
				//icons.add(whip32);
				icons.add(whip16);
				frame.setIconImages(icons);
			} catch(Exception e) {
				e.printStackTrace();
			}
			//initializeMenuBar();
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();
			frame.setResizable(resizable);
			init();
			Jframe.super.graphics = getGameComponent().getGraphics();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true); 
			frame.createBufferStrategy(2);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setClientIcon()
	{
		URL icon64 = Jframe.class.getResource("/com/client/client/images/i64.png");
		URL icon32 = Jframe.class.getResource("/com/client/client/images/i32.png");
		URL icon16 = Jframe.class.getResource("/com/client/client/images/i16.png");
		try {
			Image whip64 = ImageIO.read(icon64.openStream());
			Image whip32 = ImageIO.read(icon32.openStream());
			Image whip16 = ImageIO.read(icon16.openStream());
			ArrayList<Image> icons = new ArrayList<Image>();

			icons.add(whip16);
			frame.setIconImages(icons);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rebuildFrame(int width, int height, boolean resizable, boolean undecorated) {

		try {
			UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceNebulaLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		frame = new JFrame(Configuration.CLIENT_NAME);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent we) { 
	        String options[] = {"Yes", "No"};
	        int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "Solara", 
	        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options , options[1]);
	        if(userPrompt == JOptionPane.YES_OPTION) {
	            System.exit(0);
	        } else {
	        	 
	        }
	    }
	});
		
		setFocusTraversalKeysEnabled(false);
		JPanel gamePanel = new JPanel();
		Insets insets = this.getInsets();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = 775;
		int h = 513;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		super.setPreferredSize(new Dimension(765, 503));
		frame.setLayout(new BorderLayout());
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(this, BorderLayout.CENTER);
		gamePanel.setBackground(Color.DARK_GRAY);
		/*if(!undecorated) {
			frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
		}//try that*/
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(resizable);
		//init();
		graphics = getGameComponent().getGraphics();
		frame.setLocation(x, y);
		frame.setVisible(true); 
		frame.createBufferStrategy(2);
		

		frame.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {

				Dimension dimension = new Dimension(frame.getWidth(), frame.getHeight());
				
				gamePanel.setMinimumSize(dimension);
				gamePanel.setPreferredSize(dimension);
				gamePanel.setSize(dimension);
				
				Jframe.super.setPreferredSize(new Dimension(frame.getWidth() - 10, frame.getHeight() - 10));
				Jframe.super.revalidate();
				Jframe.super.repaint();

				Jframe.super.graphics = getGameComponent().getGraphics();

			}
			
		});
		
	}
	
	/**
	 * Our jpanel for the menu bar
	 */
	private static JPanel menuPanel;
	
	/**
	 * Initializes the menu bar
	 */
	public void initializeMenuBar() {

		
		  
		 
		menuPanel = new JPanel();

		/*
		 * Set the menu panel as non focusable
		 */
		menuPanel.setFocusable(false);

		/*
		 * Disable focus traversal keys
		 */
		menuPanel.setFocusTraversalKeysEnabled(false);

		//menuPanel.setBackground(Color.decode("0x0005"));

		menuPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		menuPanel.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

		/*
		 * Create our buttons
		 */
		JButton homeButton = createButton("", "House_icon.png", "Open the official AuroraRsps homepage.");
		JButton forumsButton = createButton("", "forums.png", "Open the official AuroraRsps forums.");

		JButton knowledgeBaseButton = createButton("", "3366503.gif", "Open the AuroraRsps Knowledge Base on the forums.");
		JButton storeButton = createButton("", "cart_icon.gif", "Open the official AuroraRsps store.");
		JButton voteButton = createButton("", "Small-checkmark.png", "Open the official AuroraRsps voting page.");
		JButton scoresButton = createButton("", "hiscores.png", "Open the official AuroraRsps Hiscores");//hahahahaah im stupid af xD why lmao

		JButton tsButton = createButton("", "discord.png", "Join the AuroraRsps discord.");

		
		/*
		 * Add our buttons to the menu panel
		 */
		menuPanel.add(homeButton);
		menuPanel.add(forumsButton);
		menuPanel.add(knowledgeBaseButton);
		menuPanel.add(storeButton);
		menuPanel.add(voteButton);
		menuPanel.add(scoresButton);
		menuPanel.add(tsButton);

		/*
		 * Add our menu panel to our frame
		 */
		frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
	}
	
	/**
	 * Creates a button on the menu panel
	 * 
	 * @param title
	 *            The Title of the button
	 * @param image
	 *            The image to display
	 * @param tooltip
	 *            The tooltip when hovering over the button
	 * @return The created button
	 */
	private JButton createButton(String title, String image, String tooltip) {
		JButton button = new JButton(title);
		if (image != null)
			button.setIcon(new ImageIcon(ResourceLoader.loadImage(image)));
		button.addActionListener(this);
		if (tooltip != null)
			button.setToolTipText(tooltip);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.WHITE);
		return button;
	}

	public URL getCodeBase() {
		try {
			return new URL("http://" + Configuration.HOST + "/");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	public String getParameter(String key) {
			return "";
	}

	public static void openUpWebSite(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url)); 	
		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if (cmd != null) {
				switch (cmd) {
				case "Home":
					openURL("http://www.Solara.org");
					break;
				case "Forums":
					openURL("http://www.Solara.org");
					break;
				case "Knowledge Base":
					openURL("http://www.Solara.org");
					break;
				case "Store":
					openURL("http://www.Solara.org");
					break;
				case "Vote":
					openURL("http://www.Solara.org");
					break;
				case "Hiscores":
					openURL("http://www.Solara.org");
					break;
				case "Join Discord":
					//String nickname = (Client.instance.getMyUsername() != null && Client.loggedIn && Client.instance.getMyUsername().length() > 2) ? TextClass.fixName(Client.instance.getMyUsername().replaceAll(" ", "%20")) : "ForumGuest";
					openURL("http://www.Solara.org");
					break;
				}

			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * Opens a URL in your default web browser
	 * 
	 * @param url
	 *            The url of the website to open
	 */
	static void openURL(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	int screenWidth = (int) screenSize.getWidth();
	int screenHeight = (int) screenSize.getHeight();
	
}