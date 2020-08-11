import org.jbox2d.common.Vec2
import kotlin.test.Test
import kotlin.test.assertTrue

class MyTest  {
	@Test
	fun pointOnLineTest() {
		val line = Line2D(Vec2(0f, 0f), Vec2(0f, 10f))
		val point = Vec2(0f, 9f)

		assertTrue(IntersectionDetector.pointOnLine(point, line))
	}

	@Test
	fun pointInCircleTest() {
		val point = Vec2(8f, 2.1f)
		val circle = Circle(6f, RigidBody(Vec2(2f, 2f)))

		assertTrue(IntersectionDetector.pointInCircle(point, circle))
	}

}