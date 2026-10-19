package frc.robot.subsystems.pivot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class Pivot {
    TalonFX pivotmotor = new TalonFX(PivotConstants.kPivotMotorID);

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
        pivotmotor.getConfigurator() .apply(motorConfigurations);
    }
    public Pivot(){
   ApplyConfigs();
}
}