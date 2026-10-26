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
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Hood {

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

// @Override
public void periodic(){
  SmartDashboard.putNumber("Hood angle", hoodMotor.getPosition().getValue().in(Degrees));
}

  public TrapezoidProfile.Constraints hoodConstraints = 
            new TrapezoidProfile.Constraints(
             HoodConstants.kMaxVelocity.in(DegreesPerSecond),
             HoodConstants.kMaxAcceleration.in(DegreesPerSecondPerSecond)
            );

      public  double KP = 0;
      public  double KI = 0;
      public  double KD = 0;

      public ProfiledPIDController hoodController = new ProfiledPIDController(KP, KI, KD, hoodConstraints);
          public Angle currentAngle = Degrees.of(30);
          public Angle targetAngle = Degrees.of(45);
          
    

 public void goToAngle( Angle currentAngle, Angle targetAngle){
   Voltage desiredVoltage = 
          Volts.of(hoodController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));
          hoodMotor.setVoltage(desiredVoltage.in(Volts));
 }

}


