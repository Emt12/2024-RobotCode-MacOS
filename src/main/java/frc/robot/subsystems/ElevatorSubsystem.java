package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Dashboard;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase{
    private CANSparkMax mElevatorMaster = new CANSparkMax(44, MotorType.kBrushless);
    //private CANSparkMax mElevatorFollower = new CANSparkMax(ElevatorConstants.kFollowerCanID, MotorType.kBrushless);
    private PIDController pidController = new PIDController(1, 0, 0);
    private Dashboard dashboard = new Dashboard();
    public ElevatorSubsystem(){
        //mElevatorFollower.follow(mElevatorMaster);
    }

    public void MoveUp(double speed){
        mElevatorMaster.set(pidController.calculate(speed, 0));
    }
    public void MoveDown(double speed){
        mElevatorMaster.set(pidController.calculate(speed, 0));
    }

    public void testStart(double speed){
        mElevatorMaster.set(speed);
    }
    public void testStop(){
        mElevatorMaster.set(0);
    }
    @Override
    public void periodic() {
        //dashboard.putOnDashboard("emtemt", mElevatorMaster.getEncoder().getPosition(), 2);
    }
}
