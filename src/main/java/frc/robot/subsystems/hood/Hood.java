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
TalonFX hoodmotor = new TalonFX(HoodConstants.kHoodMotorID);
public Hood(){
    final CurrentLimitsConfigs currentLimits =
      new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(HoodConstants.kSupplyCurrentLimit)
        .withSupplyCurrentLimitEnable(HoodConstants.kSupplyLimitEnabled)
        .withStatorCurrentLimit(HoodConstants.kstatorLimit)
        .withStatorCurrentLimitEnable(HoodConstants.kstatorLimitEnable);

    final TalonFXConfiguration motorConfigurations = new TalonFXConfiguration();
     motorConfigurations.withCurrentLimits(currentLimits);

     final MotorOutputConfigs motorOutputs = 
     new MotorOutputConfigs()
        .withInverted(HoodConstants.kInverstion)
        .withNeutralMode(HoodConstants.kNeutralMode);

    final FeedbackConfigs feedback = 
    new FeedbackConfigs()
        .withSensorToMechanismRatio(HoodConstants.kGearRatio);
}




}

