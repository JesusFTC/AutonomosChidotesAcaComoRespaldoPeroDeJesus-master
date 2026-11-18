/* ============================================================= *
 *                 Turtle Tracer — Auto-Generated                *
 *                                                               *
 *  Version: 2.7.0.                                              *
 *  Copyright (c) 2026 Matthew Allen                             *
 *                                                               *
 *  THIS FILE IS AUTO-GENERATED — DO NOT EDIT MANUALLY.          *
 *  Changes will be overwritten when regenerated.                *
 * ============================================================= */

package org.firstinspires.ftc.teamcode.Autonomous;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.api.Paths.path;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.interpolator.Interpolator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Intake.IntakeAutonomousCommand;
import org.firstinspires.ftc.teamcode.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Shooter.ShooterAutonomousCommand;
import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(
  name = "BLUE_DOWN_ShootDown_IntakeGarden_Park",
  group = "Autonomous"
)
@Configurable // Panels
public class BLUE_DOWN_ShootDown_IntakeGarden_Park extends OpMode {

  private TelemetryManager panelsTelemetry; // Panels Telemetry instance
  public Follower follower; // Pathing follower instance
  private final PoseFactory p = PoseFactory.degrees();
  private int pathState; // Current autonomous path state (state machine)
  private ElapsedTime pathTimer; // Timer for path state machine
  private Paths paths; // Paths defined in the Paths class
  private ShooterSubsystem shooterSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private ShooterAutonomousCommand shooterAutonomousCommand;
  private IntakeAutonomousCommand intakeAutonomousCommand;

  @Override
  public void init() {
    CommandScheduler.getInstance().reset();

    shooterSubsystem = new ShooterSubsystem(hardwareMap);
    intakeSubsystem = new IntakeSubsystem(hardwareMap);
    shooterAutonomousCommand = new ShooterAutonomousCommand(shooterSubsystem, intakeSubsystem);
    intakeAutonomousCommand = new IntakeAutonomousCommand(intakeSubsystem, shooterSubsystem);
    panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
    // ...
    panelsTelemetry.debug("Status", "Initialized");
    panelsTelemetry.update(telemetry);

    follower = Constants.create(hardwareMap);
    follower.setPose(p.of(60.000, 9.300, 90.000));

    pathTimer = new ElapsedTime();
    paths = new Paths(follower); // Build paths
  }

  @Override
  public void loop() {
    CommandScheduler.getInstance().run();
    follower.update(); // Update follower
    pathState = autonomousPathUpdate(); // Update autonomous state machine

    // Log values to Panels and Driver Station
    panelsTelemetry.debug("Path State", pathState);
    panelsTelemetry.debug("X", follower.pose().x());
    panelsTelemetry.debug("Y", follower.pose().y());
    panelsTelemetry.debug("Heading", follower.pose().heading());
    panelsTelemetry.update(telemetry);
  }

  public static class Paths {

    private static final PoseFactory p = PoseFactory.degrees();
    public Path DriveToShoot;
    public Path line2;
    public Path line3;
    public Path line4;

    public Paths(Follower follower) {
      // Max speeds are set through Constants.foresightConfig (Pedro's "Modifying Constants").
      // If the build fails with "cannot find symbol ... foresightConfig", your Constants class needs a public static ForesightConfig foresightConfig that your Follower is built from.
      DriveToShoot = line(p.of(60.000, 9.300, 0.0), p.of(24.982, 26.519, 0.0))
        .constant(Math.toRadians(90))
        .with(Constants.foresightConfig.maxVelocityConstraint.at(25.0));

      line2 = line(p.of(24.982, 26.519, 0.0), p.of(10.000, 12.029, 0.0))
        .constant(Math.toRadians(-90))
        .with(Constants.foresightConfig.maxVelocityConstraint.at(20.0));

      line3 = line(
        p.of(10.000, 12.029, 0.0),
        p.of(24.982, 26.519, 0.0)
      ).constant(Math.toRadians(-90));

      line4 = line(
        p.of(24.982, 26.519, 0.0),
        p.of(10.000, 92.000, 0.0)
      ).constant(Math.toRadians(-90));
    }
  }

  public int autonomousPathUpdate() {
    switch (pathState) {
      case 0:
        shooterAutonomousCommand.schedule();
        setPathState(1);
        break;
      case 1:
        if (pathTimer.milliseconds() > 4000) {
          shooterAutonomousCommand.cancel();
          shooterSubsystem.stop();
          setPathState(2);
        }
        break;
      case 2:
        follower.follow(paths.DriveToShoot);
        setPathState(3);
        break;
      case 3:
        if (!follower.isBusy()) {
          setPathState(4);
        }
        break;
      case 4:
        follower.hold(follower.pose().withHeading(-1.571));
        follower.algorithm().reset();
        setPathState(5);
        break;
      case 5:
        if (
          Math.abs(
            com.pedropathing.utils.Angle.error(
              follower.pose().heading(),
              -1.571
            )
          ) < 0.01
        ) {
          setPathState(6);
        }
        break;
      case 6:
        intakeAutonomousCommand.schedule();
        follower.follow(paths.line2);
        setPathState(7);
        break;
      case 7:
        if (!follower.isBusy()) {
          setPathState(8);
        }
        break;
      case 8:
        setPathState(9);
        break;
      case 9:
        if (pathTimer.milliseconds() > 2000) {
          intakeAutonomousCommand.cancel();
          intakeSubsystem.stop();
          setPathState(10);
        }
        break;
      case 10:
        follower.follow(paths.line3);
        setPathState(11);
        break;
      case 11:
        if (!follower.isBusy()) {
          setPathState(12);
        }
        break;
      case 12:
        follower.follow(paths.line4);
        setPathState(13);
        break;
      case 13:
        if (!follower.isBusy()) {
          setPathState(14);
        }
        break;
      case 14:
        requestOpModeStop();
        pathState = -1;
        break;
    }
    return pathState;
  }

  public void setPathState(int pState) {
    pathState = pState;
    pathTimer.reset();
  }
}
