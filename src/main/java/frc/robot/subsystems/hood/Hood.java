package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Radians;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Angle;


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
}


