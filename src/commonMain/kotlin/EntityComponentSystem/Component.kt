package EntityComponentSystem

import com.soywiz.klock.hr.HRTimeSpan
import com.soywiz.korge.component.MouseComponent
import com.soywiz.korge.component.UpdateComponent
import com.soywiz.korge.view.View

/**
 * Simple basic interface as a prototype for all components
 * A component is just a structure which adds special abilities to an [Entity], for
 * example physics, sprites, input-reacting, ...
 * The components can be added to an [Entity] so it has access to its values.
 * Every Component is updated by its [update] method -> [UpdateComponent]
 * @property view the Entity which owns this component. The component can manipulate
 * the views properties and behaviour
 * @property type The type of the Component. It's a value from type [ComponentType]
 * @see ComponentType
*/
interface Component: UpdateComponent {
    override val view: Entity
    val type: ComponentType
}


