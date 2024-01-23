package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import frc.robot.subsystems.DriveSubsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;
import frc.robot.Constants.ShuffleBoardConstants;

public class Dashboard extends SubsystemBase{
    
    private PowerDistribution pdp = new PowerDistribution();
    private AHRS m_gyro = new AHRS(SPI.Port.kMXP);
    private ShuffleboardTab driverTab = Shuffleboard.getTab(ShuffleBoardConstants.kTabName);
    
    private ComplexWidget pdpWidget;
    private ComplexWidget gyroWidget;

    protected void putOnDashboard(String key, double number, int type){
        switch(type){
            case 1://text view
                Shuffleboard.getTab(ShuffleBoardConstants.kTabName)
                            .add(key, number)
                            .withWidget(BuiltInWidgets.kField)
                            .getEntry();
            break;

            case 2://simple dial
                Shuffleboard.getTab(ShuffleBoardConstants.kTabName)
                            .add(key, number)
                            .withWidget(BuiltInWidgets.kDial)
                            .getEntry();

            case 3://number bar
                Shuffleboard.getTab(ShuffleBoardConstants.kTabName)
                            .add(key, number)
                            .withWidget(BuiltInWidgets.kNumberBar)
                            .getEntry();
            break;

            default:
                SmartDashboard.putNumber(key, number);
            break;
                        
        }
    }

    protected void putPDPWidget(){
        pdpWidget = driverTab
            .add("PDP", pdp)
            .withWidget(BuiltInWidgets.kPowerDistribution)
            .withPosition(0, 7)
            .withSize(3, 2);
    }

    protected void putGyroAngle(){
        gyroWidget = driverTab
            .add("Gyro", m_gyro)
            .withWidget(BuiltInWidgets.kGyro)
            .withPosition(12, 12)
            .withSize(4, 4);
    }
}
