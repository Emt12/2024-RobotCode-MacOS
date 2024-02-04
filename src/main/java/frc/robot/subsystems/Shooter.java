package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants.ShooterConstants;
import frc.utils.SwerveUtils;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase{
    
    private final CANSparkMax mMasterMotor = new CANSparkMax(ShooterConstants.ShooterMasterCanID, MotorType.kBrushless);
    private final CANSparkMax mFollowerMotor = new CANSparkMax(ShooterConstants.ShooterFollowerCanID, MotorType.kBrushless);
    public Shooter(){
        mFollowerMotor.setInverted(true);
        mFollowerMotor.follow(mMasterMotor);

    }
    
    public void shoot(){
        mMasterMotor.set(ShooterConstants.defaultSpeed);
    }
    public void stop(){
        mMasterMotor.set(0);
    }
}
