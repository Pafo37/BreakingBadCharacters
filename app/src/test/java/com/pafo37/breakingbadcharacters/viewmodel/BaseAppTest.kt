package com.pafo37.breakingbadcharacters.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.junit.MockitoJUnit
import org.mockito.quality.Strictness

open class BaseAppTest {

    @get:Rule
    var mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    open fun setUp() {

    }
}