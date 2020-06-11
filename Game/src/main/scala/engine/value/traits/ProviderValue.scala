package engine.value.traits

import engine.value.ValueTypes.Provider
import engine.value.{Value, ValueContext}

trait ProviderValue[R] extends Value[R] {
    protected val provider: Provider[R]

    final override def get(implicit gameContext: ValueContext): Option[R] =
        provider(gameContext)
}
