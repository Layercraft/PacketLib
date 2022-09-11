package io.layercraft.translator.data.entity

abstract class Entity (
    val state: Byte = 0,
    val airTicks: Int = 300,
    val customName: String? = null,
    val customNameVisible: Boolean = false,
    val silent: Boolean = false,
    val noGravity: Boolean = false,
    val pose: EntityPose = EntityPose.STANDING,
    val snowTicks: Int = 0,
)

enum class EntityState(
    val bitmask: Int
) {
    IS_ON_FIRE(0x01),
    IS_CROUCHED(0x02),
    IS_SPRINTING(0x08),
    IS_SWIMMING(0x10),
    IS_INVISIBLE(0x20),
    IS_GLOWING(0x40),
    IS_FLYING_WITH_ELYTRA(0x80);

    companion object {
        fun fromBitmask(bitmask: Byte): Set<EntityState> {
            return values().filter { bitmask.toInt() and it.bitmask != 0 }.toSet()
        }

        fun toBitmask(states: Set<EntityState>): Byte {
            return states.fold(0) { acc, state -> acc or state.bitmask }.toByte()
        }
    }
}

enum class EntityPose(
    val bitmask: Int
) {
    STANDING(0),
    FALL_FLYING(1),
    SLEEPING(2),
    SWIMMING(3),
    SPIN_ATTACK(4),
    SNEAKING(5),
    LONG_JUMPING(6),
    DYING(7),
    CROAKING(8),
    USING_TONGUE(9),
    ROARING(10),
    SNIFFING(11),
    EMERGING(12),
    DIGGING(13),
}
