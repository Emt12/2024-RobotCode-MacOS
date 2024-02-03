package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants.ShooterConstants;
import frc.utils.SwerveUtils;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase{
    
    private final CANSparkMax sparkMax = new CANSparkMax(21, MotorType.kBrushless);
    private final CANSparkMax sparkMax2 = new CANSparkMax(31, MotorType.kBrushless);
    
    public void setSpeed(double speed){
        sparkMax.set(speed);
        sparkMax2.set(speed);
        
    }
    public void shoot(){
        sparkMax2.setInverted(true);
        setSpeed(1);
    }
}
