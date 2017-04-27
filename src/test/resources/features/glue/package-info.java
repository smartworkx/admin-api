/**
 * <h1>Guiding principles for the writing of the glue</h1>
 * <ul>
 * <li>Step definitions do not exercise production code themselves.
 * They delegate this to helpers.</li>
 * <li> The helpers are retrieved by
 * the {@link nl.smartworkx.admin.GlueFactory} the step
 * definitions do not initiate the application context. This is
 * done lazily by the {@link nl.smartworkx.admin.GlueFactory}
 * This way tests that only use the domain or do not need a spring
 * application context are not slowed down by the initialization of
 * application context of the app (the cucumber step definitions do
 * have their own small application context)</li>
 * <li>
 * Test helpers should not contain any specific logic for cucumber
 * and therefore can be easily re-used in traditional unit tests
 * State is transferred between the step definitions by classes with
 * the prefix KnowsThe... As described in the "Cucumber for java" book.
 * </li>
 * </ul>
 *
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
package features.glue;