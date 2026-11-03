package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.DegreesPerSecondPerSecond;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Volts;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Hood extends SubsystemBase {

private Angle targetAngle = HoodConstants.kStartingAngle;

TalonFX hoodMotor = new TalonFX(HoodConstants.kHoodMotorID);
  final CurrentLimitsConfigs currentLimits =
      new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(HoodConstants.kSupplyCurrentLimit)
        .withSupplyCurrentLimitEnable(HoodConstants.kSupplyLimitEnabled)
        .withStatorCurrentLimit(HoodConstants.kstatorLimit)
        .withStatorCurrentLimitEnable(HoodConstants.kstatorLimitEnable);

     final MotorOutputConfigs motorOutputs = 
     new MotorOutputConfigs()
        .withInverted(HoodConstants.kInverstion)
        .withNeutralMode(HoodConstants.kNeutralMode);

    final FeedbackConfigs feedback = 
    new FeedbackConfigs()
        .withSensorToMechanismRatio(HoodConstants.kGearRatio);

        
    final TalonFXConfiguration motorConfigurations = 
    new TalonFXConfiguration() 
    .withCurrentLimits(currentLimits)
    .withMotorOutput(motorOutputs)
    .withFeedback(feedback);

    private void ApplyConfigs(){
hoodMotor.getConfigurator() .apply(motorConfigurations);
    }

public Hood(){
  ApplyConfigs();
}

@Logged
(name = "hoodPitch")
public Angle getAngle() {
  Angle angle = hoodMotor.getPosition().getValue();
  return angle;
}


public boolean atAngle( Angle angle ) {
//check if the current angle of the hood is within 1.5 degrees of the desired angle
return Math.abs(getAngle().in(Degrees) - angle.in(Degrees)) <= HoodConstants.kHoodAngelTolerance.in(Degrees);
}

public Voltage getVoltge(Voltage voltage){
  return this.hoodMotor.getMotorVoltage().getValue();
}

public Current getCurrent(){
  return this.hoodMotor.getStatorCurrent().getValue();
}

//@Override
public void periodic(){
  SmartDashboard.putNumber("Hood angle", hoodMotor.getPosition().getValue().in(Degrees));
}

  public TrapezoidProfile.Constraints hoodConstraints = 
            new TrapezoidProfile.Constraints(
             HoodConstants.kMaxVelocity.in(DegreesPerSecond),
             HoodConstants.kMaxAcceleration.in(DegreesPerSecondPerSecond)
            );

      public  double kP = 0;
      public  double kI = 0;
      public  double kD = 0;
      public  double kS = 0;
      public  double kG = 0;
      public  double kV = 0;

      public ProfiledPIDController hoodController = new ProfiledPIDController(kP, kI, kD, hoodConstraints);
          public Angle currentAngle = Degrees.of(30);
          public Angle desiredAngle = Degrees.of(45);
          
    

 

 public ArmFeedforward hoodArmFeedForward = 
 new ArmFeedforward(kS,  kG, kV);
 

 public void goToAngle( Angle targetAngle){
  Angle currentAngle = getAngle();

  Voltage desiredFFVoltage = Volts.of(hoodArmFeedForward.calculate( targetAngle.in(Degrees), 0 ));
  
  Voltage desiredPIDVoltage = 
          Volts.of(hoodController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));

  Voltage desiredVoltage = Volts.of( desiredFFVoltage.in(Volts) + desiredPIDVoltage.in(Volts));
  
    hoodMotor.setVoltage(desiredVoltage.in(Volts));

    public void setTargetAngle(Angle angle){
      this.targetAngle = angle;
    }
 }


public boolean atHomedPosition() { 
  throw new UnsupportedOperationException("Unimplemented method 'atHomedPosition'");
}

public void runVolts(Voltage desiredVoltage) {
   hoodMotor.setVoltage(desiredVoltage.in(Volts));
}
public void zeroEncoder() {
    throw new UnsupportedOperationException("Unimplemented method 'zeroEncoder'");
}
}


