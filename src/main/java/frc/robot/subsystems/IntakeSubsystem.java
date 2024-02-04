package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import org.photonvision.PhotonCamera;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.Limelight;

public class IntakeSubsystem {
    private PIDController pidController = new PIDController(1, 0, 0);
    private Dashboard dashboard = new Dashboard();
    private PhotonCamera photonCamera;

    public IntakeSubsystem(PhotonCamera photonCamera){
        this.photonCamera = photonCamera;
    }

    public void IntakeUp(){

    }
    public void IntakeDown(){

    }
    public void IntakeRight(){

    }
    public void IntakeLeft(){
        
    }
}
