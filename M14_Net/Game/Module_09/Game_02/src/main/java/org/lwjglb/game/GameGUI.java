package org.lwjglb.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {

    private JPanel panelMain;

    private JButton clearButton;
    private JButton resetButton;
    private JButton addButton;
    private JButton addCar;
    private JButton addPlane;
    private JButton faster;
    private JButton slower;
    private JButton left;
    private JButton right;
    private JButton up;
    private JButton down;
    private JButton addMotorcycle;
    private JButton addNetwork;


    private static Boolean clearCommand = false;
    private static Boolean resetCommand = false;
    private static Boolean addCommand = false;
    private static Boolean addCarCommand = false;
    private static Boolean addPlaneCommand = false;
    private static Boolean FasterCommand = false;
    private static Boolean SlowerCommand = false;
    private static Boolean UpCommand = false;
    private static Boolean DownCommand = false;
    private static Boolean LeftCommand = false;
    private static Boolean RightCommand = false;
    private static Boolean addMotorcycleCommand = false;
    private static Boolean addNetworkCommand = false;

    public GameGUI() {

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCommand = true;
                System.out.println(" Clear Button");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetCommand = true;
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCommand = true;
            }
        });
        addCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCarCommand = true;
            }
        });
        addPlane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlaneCommand = true;
            }
        });
        faster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FasterCommand = true;
            }
        });
        slower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SlowerCommand = true;
            }
        });

//        UP DOWN LEFT RIGHT
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpCommand = true;
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DownCommand = true;
            }
        });
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeftCommand = true;
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RightCommand = true;
            }
        });

        addMotorcycle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMotorcycleCommand = true;
            }
        });

        addNetwork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNetworkCommand = true;
            }
        });
    }


    public static void guiSetup() {
        JFrame frame = new JFrame("GameGUI");
        frame.setContentPane(new GameGUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(150,150);  //so it isn't at very top left
        frame.setVisible(true);
    }

    // Force that when reset vaue is read back as true, it is reset to false
    // so that we don't get a studder of multiple resets.
    // Also, DO NOT include a setter for this field as that would defeat the design.
    public static Boolean getClearCommand() {
        boolean returnValue = false;
        if (clearCommand == true) {
            clearCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getResetCommand() {
        boolean returnValue = false;
        if (resetCommand == true) {
            resetCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getAddCommand() {
        boolean returnValue = false;
        if (addCommand == true) {
            addCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getAddCarCommand() {
        boolean returnValue = false;
        if (addCarCommand == true) {
            addCarCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getAddPlaneCommand() {
        boolean returnValue = false;
        if (addPlaneCommand == true) {
            addPlaneCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getFasterCommand() {
        boolean returnValue = false;
        if (FasterCommand == true) {
            FasterCommand = false;
            returnValue = true;
        }
        return returnValue;
    }
    public static Boolean getSlowerCommand() {
        boolean returnValue = false;
        if (SlowerCommand == true) {
            SlowerCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getUpCommand() {
        boolean returnValue = false;
        if (UpCommand == true) {
            UpCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getDownCommand() {
        boolean returnValue = false;
        if (DownCommand == true) {
            DownCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getLeftCommand() {
        boolean returnValue = false;
        if (LeftCommand == true) {
            LeftCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getRightCommand() {
        boolean returnValue = false;
        if (RightCommand == true) {
            RightCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getAddMotorcycleCommand() {
        boolean returnValue = false;
        if (addMotorcycleCommand == true) {
            addMotorcycleCommand = false;
            returnValue = true;
        }
        return returnValue;
    }

    public static Boolean getAddNetworkCommand() {
        boolean returnValue = false;
        if (addNetworkCommand == true) {
            addNetworkCommand = false;
            returnValue = true;
        }
        return returnValue;
    }
}


