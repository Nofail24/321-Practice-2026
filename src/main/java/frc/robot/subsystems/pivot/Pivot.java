package frc.robot.subsystems.pivot;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.DegreesPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.hood.HoodConstants;

public class Pivot {
    TalonFX pivotMotor = new TalonFX(PivotConstants.kPivotMotorID);

 final CurrentLimitsConfigs currentLimits = 
     new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(PivotConstants.kSupplyCurrentLimits)
        .withSupplyCurrentLimitEnable(PivotConstants.kSupplyLimitsEnable)
        .withStatorCurrentLimit(PivotConstants.kStatorLimit)
        .withStatorCurrentLimitEnable(PivotConstants.kStatorLimitsEnable);

final MotorOutputConfigs motorOutputs =
    new MotorOutputConfigs()
    .withInverted(PivotConstants.kInverstion)
    .withNeutralMode(PivotConstants.kNeutralMode);

    final FeedbackConfigs feedback = 
    new FeedbackConfigs()
        .withSensorToMechanismRatio(PivotConstants.kGearRatio);

        final TalonFXConfiguration motorConfigurations = 
    new TalonFXConfiguration()
    .withCurrentLimits(currentLimits)
    .withMotorOutput(motorOutputs)
    .withFeedback(feedback);
    
    private void ApplyConfigs(){
        pivotMotor.getConfigurator() .apply(motorConfigurations);
    }
    public Pivot(){
   ApplyConfigs();
}

@Logged
(name = "pivotPitch")
public Angle getAngle() {
    Angle angle = pivotMotor.getPosition().getValue();
    return angle;
}

public boolean atAngle( Angle angle) {
    return Math.abs(getAngle().in(Degrees) - angle.in(Degrees)) <= PivotConstants.kPivotAngleTolerance.in(Degrees);
}

public Voltage getVoltage(Voltage voltage){
    return this.pivotMotor.getMotorVoltage().getValue();
}

public Current getCurrent(){
    return this.pivotMotor.getStatorCurrent().getValue();
}

//@Override
public void periodic(){
    SmartDashboard.putNumber("Pivot angle", pivotMotor.getPosition().getValue().in(Degrees));
}
 public TrapezoidProfile.Constraints PivotConstraints = 
            new TrapezoidProfile.Constraints(
             PivotConstants.kMaxVelocity.in(DegreesPerSecond),
             PivotConstants.kMaxAcceleration.in(DegreesPerSecondPerSecond)
            );

      public  double kP = 0;
      public  double kI = 0;
      public  double kD = 0;
      public  double kS = 0;
      public  double kG = 0;
      public  double kV = 0;

      public ProfiledPIDController pivotController = new ProfiledPIDController(kP, kI, kD, PivotConstraints);
          public Angle currentAngle = Degrees.of(30);
          public Angle targetAngle = Degrees.of(45);
          
    

 

 public ArmFeedforward pivotArmFeedForward = 
 new ArmFeedforward(kS,  kG, kV);
 

 public void goToAngle( Angle targetAngle){
  Angle currentAngle = getAngle();

  Voltage desiredFFVoltage = Volts.of(pivotArmFeedForward.calculate( targetAngle.in(Degrees), 0 ));
  
  Voltage desiredPIDVoltage = 
          Volts.of(pivotController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));

  Voltage desiredVoltage = Volts.of( desiredFFVoltage.in(Volts) + desiredPIDVoltage.in(Volts));
  
    pivotMotor.setVoltage(desiredVoltage.in(Volts));
}
}