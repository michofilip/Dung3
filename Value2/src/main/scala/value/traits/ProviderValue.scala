package value.traits

import value.ValueTypes.Provider
import value.{Value, ValueContext}

trait ProviderValue[R] extends Value[R] {
    protected val provider: Provider[R]

    final override def get(implicit gameContext: ValueContext): Option[R] =
        provider(gameContext)
}
