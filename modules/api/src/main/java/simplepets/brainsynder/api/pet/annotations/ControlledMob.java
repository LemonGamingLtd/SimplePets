package simplepets.brainsynder.api.pet.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Any PetType that uses a {@link simplepets.brainsynder.api.entity.misc.IEntityControllerPet} should have this annotation
 * to mark the pet for a warning if there is ever any issues with the controlled mob.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ControlledMob {}