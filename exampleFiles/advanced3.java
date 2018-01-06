//
// Copyright 2016 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

#import <CoreGraphics/CoreGraphics.h>
#import <EarlGrey/GREYConstants.h>
#import <EarlGrey/GREYDefines.h>
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol GREYMatcher;

/**
 *  EarlGrey matchers for UI elements.
 */
@interface GREYMatchers : NSObject

/**
 *  Matcher for application's key window.
 *
 *  @return A matcher for the application's key window.
 */
+ (id<GREYMatcher>)matcherForKeyWindow;

/**
 *  Matcher for UI element with the provided accessibility @c label.
 *
 *  @param label The accessibility label to be matched.
 *
 *  @return A matcher for the accessibility label of an accessible element.
 */
+ (id<GREYMatcher>)matcherForAccessibilityLabel:(NSString *)label;

/**
 *  Matcher for UI element with the provided accessibility ID @c accessibilityID.
 *
 *  @param accessibilityID The accessibility ID to be matched.
 *
 *  @return A matcher for the accessibility ID of an accessible element.
 */
+ (id<GREYMatcher>)matcherForAccessibilityID:(NSString *)accessibilityID;

/**
 *  Matcher for UI element with the provided accessibility @c value.
 *
 *  @param value The accessibility value to be matched.
 *
 *  @return A matcher for the accessibility value of an accessible element.
 */
+ (id<GREYMatcher>)matcherForAccessibilityValue:(NSString *)value;

/**
 *  Matcher for UI element with the provided accessibility @c traits.
 *
 *  @param traits The accessibility traits to be matched.
 *
 *  @return A matcher for the accessibility traits of an accessible element.
 *
 */
+ (id<GREYMatcher>)matcherForAccessibilityTraits:(UIAccessibilityTraits)traits;

/**
 *  Matcher for UI element with the provided accessiblity @c hint.
 *
 *  @param hint The accessibility hint to be matched.
 *
 *  @return A matcher for the accessibility hint of an accessible element.
 */
+ (id<GREYMatcher>)matcherForAccessibilityHint:(NSString *)hint;

/**
 *  Matcher for UI element with accessiblity focus.
 *
 *  @return A matcher for the accessibility focus of an accessible element.
 */
+ (id<GREYMatcher>)matcherForAccessibilityElementIsFocused;

/**
 *  Matcher for UI elements of type UIButton, UILabel, UITextField or UITextView displaying the
 *  provided @c inputText.
 *
 *  @param text The text to be matched in the UI elements.
 *
 *  @return A matcher to check for any UI elements with a text field containing the given text.
 */
+ (id<GREYMatcher>)matcherForText:(NSString *)text;

/**
 *  Matcher for element that is the first responder.
 *
 *  @return A matcher that verifies if a UI element is the first responder.
 */
+ (id<GREYMatcher>)matcherForFirstResponder;

/**
 *  Matcher to check if system alert view is shown.
 *
 *  @return A matcher to check if a system alert view is being shown.
 */
+ (id<GREYMatcher>)matcherForSystemAlertViewShown;

/**
 *  Matcher for UI element whose percent visible area (of its accessibility frame) exceeds the
 *  given @c percent.
 *
 *  @param percent The percent visible area that the UI element being matched has to be visible.
 *                 Allowed values for @c percent are [0,1] inclusive.
 *
 *  @return A matcher that checks if a UI element has a visible area at least equal
 *          to a minimum value.
 */
+ (id<GREYMatcher>)matcherForMinimumVisiblePercent:(CGFloat)percent;

/**
 *  Matcher for UI element that is sufficiently visible to the user. EarlGrey considers elements
 *  that are more than @c kElementSufficientlyVisiblePercentage (75 %) visible areawise to be
 *  sufficiently visible.
 *
 *  @return A matcher intialized with a visibility percentage that confirms an element is
 *          sufficiently visible.
 */
+ (id<GREYMatcher>)matcherForSufficientlyVisible;

/**
 *  Matcher for UI element that is not visible to the user at all i.e. it has a zero visible area.
 *
 *  @return A matcher for verifying if an element is not visible.
 */
+ (id<GREYMatcher>)matcherForNotVisible;

/**
 *  Matcher for UI element that matches EarlGrey's criteria for user interaction. Currently it must
 *  satisfy at least the following criteria:
 *  1) At least a few pixels of the element are visible to the user.
 *  2) The element's accessibility activation point OR the center of the element's visible area
 *     is completely visible.
*
 *  @return A matcher that checks if a UI element is interactable.
 */
+ (id<GREYMatcher>)matcherForInteractable;

/**
 *  Matcher to check if a UI element is accessible.
 *
 *  @return A matcher that checks if a UI element is accessible.
 */
+ (id<GREYMatcher>)matcherForAccessibilityElement;

/**
 *  Matcher for elements that are instances of the provided @c klass or any class that inherits from
 *  it.
 *
 *  @param klass A class.
 *
 *  @return A matcher that checks if the given element's class is the provided @c klass or any of
 *          its derived classes.
 */
+ (id<GREYMatcher>)matcherForKindOfClass:(Class)klass;

/**
 *  Matcher for matching UIProgressView's values. Use greaterThan, greaterThanOrEqualTo,
 *  lessThan etc to create @c comparisonMatcher. For example, to match the UIProgressView
 *  elements that have progress value greater than 50.2, use
 *  @code [GREYMatchers matcherForProgress:grey_greaterThan(@(50.2))] @endcode. In case if an
 *  unimplemented matcher is required, please implement it similar to @c grey_closeTo.
 *
 *  @param comparisonMatcher The matcher with the value to check the progress against.
 *
 *  @return A matcher for checking a UIProgessView's value.
 */
+ (id<GREYMatcher>)matcherForProgress:(id<GREYMatcher>)comparisonMatcher;

/**
 *  Matcher for UI element that respond to the provided @c sel.
 *
 *  @param sel The selector to be responded to.
 *
 *  @return A matcher to check if a UI element's class responds to a particular selector.
 */
+ (id<GREYMatcher>)matcherForRespondsToSelector:(SEL)sel;

/**
 *  Matcher for UI element that conform to the provided @c protocol.
 *
 *  @param protocol The protocol that the UI element must conform to.
 *
 *  @return A matcher to check if a UI element's class confirms to a particular protocol.
 */
+ (id<GREYMatcher>)matcherForConformsToProtocol:(Protocol *)protocol;

/**
 *  Matcher that matches UI element based on the presence of an ancestor in its hierarchy.
 *  The given matcher is used to match decendants.
 *
 *  @param ancestorMatcher The ancestor UI element whose descendants are to be matched.
 *
 *  @return A matcher to check if a UI element is the descendant of another.
 */
+ (id<GREYMatcher>)matcherForAncestor:(id<GREYMatcher>)ancestorMatcher;

/**
 *  Matcher that matches any UI element with a descendant matching the given matcher.
 *
 *  @param descendantMatcher A matcher being checked to be a descendant
 *                           of the UI element being checked.
 *
 *  @return A matcher to check if a the specified element is in a descendant of another UI element.
 */
+ (id<GREYMatcher>)matcherForDescendant:(id<GREYMatcher>)descendantMatcher;

/**
 *  Matcher that matches UIButton that has title label as @c text.
 *
 *  @param title The title to be checked on the UIButtons being matched.
 *
 *  @return A matcher to confirm UIButton titles.
 */
+ (id<GREYMatcher>)matcherForButtonTitle:(NSString *)title;

/**
 *  Matcher that matches UIScrollView that has contentOffset as @c offset.
 *
 *  @param offset The content offset to be checked on the UIScrollView being
 *                matched.
 *
 *  @return A matcher to confirm UIScrollView content offset.
 */
+ (id<GREYMatcher>)matcherForScrollViewContentOffset:(CGPoint)offset;

/**
 *  Matcher that matches UIStepper with value as @c value.
 *
 *  @param value A value that the UIStepper should be checked for.
 *
 *  @return A matcher for checking UIStepper values.
 */
+ (id<GREYMatcher>)matcherForStepperValue:(double)value;

/**
 *  Matcher that matches a UISlider's value.
 *
 *  @param valueMatcher A matcher for the UISlider's value. You must provide a valid
 *                      @c valueMatcher for the floating point value comparison. The
 *                      @c valueMatcher should be of the type @c closeTo, @c greaterThan,
 *                      @c lessThan, @c lessThanOrEqualTo, @c greaterThanOrEqualTo. The
 *                      value matchers should account for any loss in precision for the given
 *                      floating point value. If you are using @c grey_closeTo, use delta diff as
 *                      @c kGREYAcceptableFloatDifference. In case if an unimplemented matcher
 *                      is required, please implement it similar to @c grey_closeTo.
 *
 *  @return A matcher for checking a UISlider's value.
 */
+ (id<GREYMatcher>)matcherForSliderValueMatcher:(id<GREYMatcher>)valueMatcher;

/**
 *  Matcher that matches UIPickerView that has a column set to @c value.
 *
 *  @param column The column of the UIPickerView to be matched.
 *  @param value  The value that should be set in the column of the UIPickerView.
 *
 *  @return A matcher to check the value in a particular column of a UIPickerView.
 */
+ (id<GREYMatcher>)matcherForPickerColumn:(NSInteger)column setToValue:(NSString *)value;

/**
 *  Matcher that matches UIDatePicker that is set to @c value.
 *
 *  @param value The date value that should be present in the UIDatePicker
 *
 *  @return A matcher for a date in a UIDatePicker.
 */
+ (id<GREYMatcher>)matcherForDatePickerValue:(NSDate *)value;

/**
 *  Matcher that verifies whether an element, that is a UIControl, is enabled.
 *
 *  @return A matcher for checking whether a UI element is an enabled UIControl.
 */
+ (id<GREYMatcher>)matcherForEnabledElement;

/**
 *  Matcher that verifies whether an element, that is a UIControl, is selected.
 *
 *  @return A matcher for checking whether a UI element is a selected UIControl.
 */
+ (id<GREYMatcher>)matcherForSelectedElement;

/**
 *  Matcher that verifies whether a view has its userInteractionEnabled property set to @c YES.
 *
 *  @return A matcher for checking whether a view' userInteractionEnabled property is set to @c YES.
 */
+ (id<GREYMatcher>)matcherForUserInteractionEnabled;

/**
 *  Matcher that verifies that the selected element satisfies the given constraints to the
 *  reference element.
 *  Usage:
 *  @code
 *  GREYLayoutConstraint *constraint1 = [GREYLayoutConstraint layoutConstraintWithAttribute ... ];
 *  GREYLayoutConstraint *constraint2 = [GREYLayoutConstraint layoutConstraintForDirection ... ];
 *  id<GREYMatcher> *matcher = [GREYMatcher matcherForConstraints:@[ constraint1, constraint2 ]
 *                                toReferenceElementMatching:aReferenceElementMatcher];
 *  [EarlGrey selectElementWithMatcher ...] assertWithMatcher:matcher];
 *  @endcode
 *
 *  @param constraints             The constraints to be matched.
 *  @param referenceElementMatcher The reference element with the correct constraints.
 *
 *  @remark Constraints are often represented using floating point numbers. Floating point
 *          arithmetic can often induce errors based on the way the numbers are represented in
 *          hardware; hence, floating point comparisons use a margin value
 *          @c kGREYAcceptableFloatDifference that is used for adding accuracy to such arithmetic.
 *
 *  @return A matcher to verify the GREYLayoutConstraints on a UI element.
 */
+ (id<GREYMatcher>)matcherForConstraints:(NSArray *)constraints
              toReferenceElementMatching:(id<GREYMatcher>)referenceElementMatcher;

/**
 *  Matcher primarily for asserting that the element is @c nil or not found.
 *
 *  @return A matcher to check if a specified element is @c nil or not found.
 */
+ (id<GREYMatcher>)matcherForNil;

/**
 *  Matcher for asserting that the element exists in the UI hierarchy (i.e. not @c nil).
 *
 *  @return A matcher to check if a specified element is not @c nil.
 */
+ (id<GREYMatcher>)matcherForNotNil;

/**
 *  Matcher for toggling the switch control.
 *
 *  @param on The state of the switch control. The switch control is in ON state if @c on is @c YES
 *            or OFF state if @c on is NO.
 *
 *  @return A matcher to check if a UISwitch is on or off.
 */
+ (id<GREYMatcher>)matcherForSwitchWithOnState:(BOOL)on;

/**
 *  A Matcher for NSNumbers that matches when the examined number is within a specified @c delta
 *  from the specified value.
 *
 *  @param value The expected value of the number being matched.
 *
 *  @param delta The delta within which matches are allowed
 *
 *  @return A matcher that checks if a number is close to a specified @c value.
 */
+ (id<GREYMatcher>)matcherForCloseTo:(double)value delta:(double)delta;

/**
 *  A Matcher that matches against any object, including @c nils.
 *
 *  @return A matcher that matches any object.
 */
+ (id<GREYMatcher>)matcherForAnything;

/**
 *  A Matcher that checks if a provided object is equal to the specified @c value. The equality is
 *  determined by calling the @c isEqual: method of the object being examined. In case the @c
 *  value is @c nil, then the object itself is checked to be @c nil.
 *
 *  @param value  The value to be checked for equality. Please ensure that scalar types are
 *                passed in as boxed (object) values.
 *
 *  @return A matcher that checks if an object is equal to the provided one.
 */
+ (id<GREYMatcher>)matcherForEqualTo:(id)value;

/**
 *  A Matcher that checks if a provided object is less than a specified @c value. The comparison
 *  is made by calling the @c compare: method of the object being examined.
 *
 *  @param value The value to be compared, which should return @c NSOrderedDescending. Please
 *               ensure that scalar values are passed in as boxed (object) values.
 *
 *  @return A matcher that checks an object is lesser than another provided @c value.
 */
+ (id<GREYMatcher>)matcherForLessThan:(id)value;

/**
 *  A Matcher that checks if a provided object is greater than a specified @c value. The comparison
 *  is made by calling the @c compare: method of the object being examined.
 *
 *  @param value The value to be compared, which should return @c NSOrderedAscending. Please
 *               ensure that scalar values are passed in as boxed (object) values.
 *
 *  @return A matcher that checks an object is greater than another provided @c value.
 */
+ (id<GREYMatcher>)matcherForGreaterThan:(id)value;

/**
 *  Matcher that matches a UIScrollView scrolled to content @c edge.
 *
 *  @param edge The content edge UIScrollView should be scrolled to.
 *
 *  @return A matcher that matches a UIScrollView scrolled to content @c edge.
 */
+ (id<GREYMatcher>)matcherForScrolledToContentEdge:(GREYContentEdge)edge;

/**
 *  Matcher that matches a UITextField's content.
 *
 *  @param value The text string contained inside the UITextField.
 *
 *  @return A matcher that matches the value inside a UITextField.
 */
+ (id<GREYMatcher>)matcherForTextFieldValue:(NSString *)value;

@end

#if !(GREY_DISABLE_SHORTHAND)

/** Shorthand for GREYMatchers::matcherForKeyWindow. */
GREY_EXPORT id<GREYMatcher> grey_keyWindow(void);

/** Shorthand for GREYMatchers::matcherForAccessibilityLabel:. */
GREY_EXPORT id<GREYMatcher> grey_accessibilityLabel(NSString *label);

/** Shorthand for GREYMatchers::matcherForAccessibilityID:. */
GREY_EXPORT id<GREYMatcher> grey_accessibilityID(NSString *accessibilityID);

/** Shorthand for GREYMatchers::matcherForAccessibilityValue:. */
GREY_EXPORT id<GREYMatcher> grey_accessibilityValue(NSString *grey_accessibilityValue);

/** Shorthand for GREYMatchers::matcherForAccessibilityTraits:. */
GREY_EXPORT id<GREYMatcher> grey_accessibilityTrait(UIAccessibilityTraits traits);

/** Shorthand for GREYMatchers::matcherForAccessibilityElementIsFocused. */
GREY_EXPORT id<GREYMatcher> grey_accessibilityFocused(void);

/** Shorthand for GREYMatchers::matcherForAccessibilityHint:. */
GREY_EXPORT id<GREYMatcher> grey_accessibilityHint(NSString *hint);

/** Shorthand for GREYMatchers::matcherForText:. */
GREY_EXPORT id<GREYMatcher> grey_text(NSString *inputText);

/** Shorthand for GREYMatchers::matcherForFirstResponder. */
GREY_EXPORT id<GREYMatcher> grey_firstResponder(void);

/** Shorthand for GREYMatchers::matcherForSystemAlertViewShown. */
GREY_EXPORT id<GREYMatcher> grey_systemAlertViewShown(void);

/** Shorthand for GREYMatchers::matcherForMinimumVisiblePercent:. */
GREY_EXPORT id<GREYMatcher> grey_minimumVisiblePercent(CGFloat percent);

/** Shorthand for GREYMatchers::matcherForSufficientlyVisible. */
GREY_EXPORT id<GREYMatcher> grey_sufficientlyVisible(void);

/** Shorthand for GREYMatchers::matcherForInteractable. */
GREY_EXPORT id<GREYMatcher> grey_interactable(void);

/** Shorthand for GREYMatchers::matcherForNotVisible. */
GREY_EXPORT id<GREYMatcher> grey_notVisible(void);

/** Shorthand for GREYMatchers::matcherForAccessibilityElement. */
GREY_EXPORT id<GREYMatcher> grey_accessibilityElement(void);

/** Shorthand for GREYMatchers::matcherForKindOfClass:. */
GREY_EXPORT id<GREYMatcher> grey_kindOfClass(Class klass);

/** Shorthand for GREYMatchers::matcherForProgress:. */
GREY_EXPORT id<GREYMatcher> grey_progress(id<GREYMatcher> comparisonMatcher);

/** Shorthand for GREYMatchers::matcherForRespondsToSelector:. */
GREY_EXPORT id<GREYMatcher> grey_respondsToSelector(SEL sel);

/** Shorthand for GREYMatchers::matcherForConformsToProtocol:. */
GREY_EXPORT id<GREYMatcher> grey_conformsToProtocol(Protocol *protocol);

/** Shorthand for GREYMatchers::matcherForAncestor:. */
GREY_EXPORT id<GREYMatcher> grey_ancestor(id<GREYMatcher> ancestorMatcher);

/** Shorthand for GREYMatchers::matcherForDescendant:. */
GREY_EXPORT id<GREYMatcher> grey_descendant(id<GREYMatcher> descendantMatcher);

/** Shorthand for GREYMatchers::matcherForButtonTitle:. */
GREY_EXPORT id<GREYMatcher> grey_buttonTitle(NSString *text);

/** Shorthand for GREYMatchers::matcherForScrollViewContentOffset:. */
GREY_EXPORT id<GREYMatcher> grey_scrollViewContentOffset(CGPoint offset);

/** Shorthand for GREYMatchers::matcherForStepperValue:. */
GREY_EXPORT id<GREYMatcher> grey_stepperValue(double value);

/** Shorthand for GREYMatchers::matcherForSliderValueMatcher:. */
GREY_EXPORT id<GREYMatcher> grey_sliderValueMatcher(id<GREYMatcher> valueMatcher);

/** Shorthand for GREYMatchers::matcherForPickerColumn:setToValue:. */
GREY_EXPORT id<GREYMatcher> grey_pickerColumnSetToValue(NSInteger column, NSString *value);

/** Shorthand for GREYMatchers::matcherForDatePickerValue:. */
GREY_EXPORT id<GREYMatcher> grey_datePickerValue(NSDate *date);

/** Shorthand for GREYMatchers::matcherForEnabledElement. */
GREY_EXPORT id<GREYMatcher> grey_enabled(void);

/** Shorthand for GREYMatchers::matcherForSelectedElement. */
GREY_EXPORT id<GREYMatcher> grey_selected(void);

/** Shorthand for GREYMatchers::matcherForUserInteractionEnabled. */
GREY_EXPORT id<GREYMatcher> grey_userInteractionEnabled(void);

/** Shorthand for GREYMatchers::matcherForConstraints:toReferenceElementMatching:. */
GREY_EXPORT id<GREYMatcher> grey_layout(NSArray *constraints,
                                        id<GREYMatcher> referenceElementMatcher);

/** Shorthand for GREYMatchers::matcherForNil. */
GREY_EXPORT id<GREYMatcher> grey_nil(void);

/** Shorthand for GREYMatchers::matcherForNotNil. */
GREY_EXPORT id<GREYMatcher> grey_notNil(void);

/** Shorthand for GREYMatchers::matcherForSwitchWithOnState:. */
GREY_EXPORT id<GREYMatcher> grey_switchWithOnState(BOOL on);

/** Shorthand for GREYMatchers::matcherForCloseTo:. */
GREY_EXPORT id<GREYMatcher> grey_closeTo(double value, double delta);

/** Shorthand for GREYMatchers::matcherForAnything. */
GREY_EXPORT id<GREYMatcher> grey_anything(void);

/** Shorthand for GREYMatchers::matcherForEqualTo:. */
GREY_EXPORT id<GREYMatcher> grey_equalTo(id value);

/** Shorthand for GREYMatchers::matcherForLessThan:. */
GREY_EXPORT id<GREYMatcher> grey_lessThan(id value);

/** Shorthand for GREYMatchers::matcherForGreaterThan:. */
GREY_EXPORT id<GREYMatcher> grey_greaterThan(id value);

/** Shorthand for GREYMatchers::matcherForScrolledToContentEdge:. */
GREY_EXPORT id<GREYMatcher> grey_scrolledToContentEdge(GREYContentEdge edge);

/** Shorthand for GREYMatchers::matcherForTextFieldValue:. */
GREY_EXPORT id<GREYMatcher> grey_textFieldValue(NSString *value);

#endif // GREY_DISABLE_SHORTHAND

NS_ASSUME_NONNULL_END

41
Could not generate the following methods for GREYMatchers
	 matcherForKindOfClass: misses Class
	 matcherForRespondsToSelector: misses SEL
	 matcherForConformsToProtocol: misses Protocol *
	 matcherForStepperValue: misses double
	 matcherForDatePickerValue: misses NSDate *
	 matcherForConstraints:toReferenceElementMatching: misses NSArray *
	 matcherForSwitchWithOnState: misses BOOL
	 matcherForCloseTo:delta: misses double,double
	 matcherForEqualTo: misses id
	 matcherForLessThan: misses id
	 matcherForGreaterThan: misses id
package com.wix.detox.espresso;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.Tap;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by simonracz on 10/07/2017.
 */

public class DetoxAction {
    private static final String LOG_TAG = "detox";

    private DetoxAction() {
        // static class
    }

    public static ViewAction multiClick(int times) {
        return actionWithAssertions(new GeneralClickAction(new MultiTap(times), GeneralLocation.CENTER, Press.FINGER, 0, 0));
    }

    public static ViewAction tapAtLocation(final int x, final int y) {
        final int px = UiAutomatorHelper.convertDiptoPix(x);
        final int py = UiAutomatorHelper.convertDiptoPix(y);
        CoordinatesProvider c = new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view) {
                final int[] xy = new int[2];
                view.getLocationOnScreen(xy);
                final float fx = xy[0] + px;
                final float fy = xy[1] + py;
                float[] coordinates = {fx, fy};
                return coordinates;
            }
        };
        return actionWithAssertions(new GeneralClickAction(
                Tap.SINGLE, c, Press.FINGER, InputDevice.SOURCE_UNKNOWN, MotionEvent.BUTTON_PRIMARY));
    }

    /**
     * Scrolls to the edge of the given scrollable view.
     *
     * Edge
     * 1 -> Left
     * 2 -> Right
     * 3 -> Top
     * 4 -> Bottom
     *
     * @param edge
     * @return ViewAction
     */
    public static ViewAction scrollToEdge(final int edge) {
        return actionWithAssertions(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(View.class), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "scrollToEdge";
            }

            @Override
            public void perform(UiController uiController, View view) {
                Class<?> recyclerViewClass = null;
                try {
                    recyclerViewClass = Class.forName(RecyclerViewScrollListener.CLASS_RECYCLERVIEW);
                } catch (ClassNotFoundException e) {
                    // ok
                }
                if (view instanceof AbsListView) {
                    RNScrollListener l = new RNScrollListener((AbsListView) view);
                    do {
                        ScrollHelper.performOnce(uiController, view, edge);
                    } while (l.didScroll());
                    l.cleanup();
                } else if (view instanceof ScrollView) {
                    int prevScrollY = view.getScrollY();
                    while (true) {
                        ScrollHelper.performOnce(uiController, view, edge);
                        int currentScrollY = view.getScrollY();
                        if (currentScrollY == prevScrollY) break;
                        prevScrollY = currentScrollY;
                    }
                } else if (view instanceof HorizontalScrollView) {
                    int prevScrollX = view.getScrollX();
                    while (true) {
                        ScrollHelper.performOnce(uiController, view, edge);
                        int currentScrollX = view.getScrollX();
                        if (currentScrollX == prevScrollX) break;
                        prevScrollX = currentScrollX;
                    }
                } else if (recyclerViewClass != null && recyclerViewClass.isInstance(view)) {
                    RecyclerViewScrollListener l = new RecyclerViewScrollListener(view);
                    do {
                        ScrollHelper.performOnce(uiController, view, edge);
                    } while (l.didScroll());
                    l.cleanup();
                } else {
                    throw new RuntimeException(
                            "Only descendants of AbsListView, ScrollView, HorizontalScrollView and RecyclerView are supported");
                }
            }
        });
    }

    /**
     * Scrolls the View in a direction by the Density Independent Pixel amount.
     *
     * Direction
     * 1 -> left
     * 2 -> Right
     * 3 -> Up
     * 4 -> Down
     *
     * @param direction Direction to scroll
     * @param amountInDP Density Independent Pixels
     *
     */
    public static ViewAction scrollInDirection(final int direction, final double amountInDP) {
        return actionWithAssertions(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(View.class), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "scrollInDirection";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ScrollHelper.perform(uiController, view, direction, amountInDP);
            }
        });
    }

    private final static float EDGE_FUZZ_FACTOR = 0.083f;

    /**
     * Swipes the View in a direction.
     *
     * Direction
     * 1 -> left
     * 2 -> Right
     * 3 -> Up
     * 4 -> Down
     *
     * @param direction Direction to scroll
     * @param fast true if fast, false if slow
     *
     */
    public static ViewAction swipeInDirection(final int direction, boolean fast) {
        if (fast) {
            switch (direction) {
                case 1:
                    return swipeLeft();
                case 2:
                    return swipeRight();
                case 3:
                    return swipeUp();
                case 4:
                    return swipeDown();
                default:
                    throw new RuntimeException("Unsupported swipe direction: " + direction);
            }
        }
        switch (direction) {
            case 1:
                return actionWithAssertions(new GeneralSwipeAction(Swipe.SLOW,
                        translate(GeneralLocation.CENTER_RIGHT, -EDGE_FUZZ_FACTOR, 0),
                        GeneralLocation.CENTER_LEFT, Press.FINGER));
            case 2:
                return actionWithAssertions(new GeneralSwipeAction(Swipe.SLOW,
                        translate(GeneralLocation.CENTER_LEFT, EDGE_FUZZ_FACTOR, 0),
                        GeneralLocation.CENTER_RIGHT, Press.FINGER));
            case 3:
                return actionWithAssertions(new GeneralSwipeAction(Swipe.SLOW,
                        translate(GeneralLocation.BOTTOM_CENTER, 0, -EDGE_FUZZ_FACTOR),
                        GeneralLocation.TOP_CENTER, Press.FINGER));
            case 4:
                return actionWithAssertions(new GeneralSwipeAction(Swipe.SLOW,
                        translate(GeneralLocation.TOP_CENTER, 0, EDGE_FUZZ_FACTOR),
                        GeneralLocation.BOTTOM_CENTER, Press.FINGER));
            default:
                throw new RuntimeException("Unsupported swipe direction: " + direction);
        }
    }

    private static CoordinatesProvider translate(final CoordinatesProvider coords,
                                                 final float dx, final float dy) {
        return new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view) {
                float xy[] = coords.calculateCoordinates(view);
                xy[0] += dx * view.getWidth();
                xy[1] += dy * view.getHeight();
                return xy;
            }
        };
    }

}

6
Could not generate the following methods for DetoxAction
	 swipeInDirection misses boolean
	 translate misses CoordinatesProvider,float,float
package com.wix.detox.espresso;

import android.view.View;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * Created by simonracz on 10/07/2017.
 */

public class DetoxMatcher {

    private DetoxMatcher() {
        // static class
    }

    public static Matcher<View> matcherForText(String text) {
        // return anyOf(withText(text), withContentDescription(text));
        return withText(text);
    }

    public static Matcher<View> matcherForContentDescription(String contentDescription) {
        return withContentDescription(contentDescription);
    }

    public static Matcher<View> matcherForTestId(String testId) {
        return withTagValue(is((Object) testId));
    }

    public static Matcher<View> matcherForAnd(Matcher<View> m1, Matcher<View> m2) {
        return allOf(m1, m2);
    }

    public static Matcher<View> matcherForOr(Matcher<View> m1, Matcher<View> m2) {
        return anyOf(m1, m2);
    }

    public static Matcher<View> matcherForNot(Matcher<View> m) {
        return not(m);
    }

    public static Matcher<View> matcherWithAncestor(Matcher<View> m, Matcher<View> ancestorMatcher) {
        return allOf(m, isDescendantOfA(ancestorMatcher));
    }

    public static Matcher<View> matcherWithDescendant(Matcher<View> m, Matcher<View> descendantMatcher) {
        return allOf(m, hasDescendant(descendantMatcher));
    }

    public static Matcher<View> matcherForClass(final String className) {
        try {
            Class cls = Class.forName(className);
            return isAssignableFrom(cls);
        } catch (ClassNotFoundException e) {
            // empty
        }
        return new BaseMatcher<View>() {
            @Override
            public boolean matches(Object item) {
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Class " + className + " not found on classpath. Are you using full class name?");
            }
        };
    }

    public static Matcher<View> matcherForSufficientlyVisible() {
        return isDisplayingAtLeast(75);
    }

    // Special ViewAssertion is needed for GONE views
    @Deprecated
    public static Matcher<View> matcherForNotVisible() {
        return not(isDisplayed());
    }

    public static Matcher<View> matcherForNotNull() {
        return notNullValue(android.view.View.class);
    }

    public static Matcher<View> matcherForNull() {
        return nullValue(android.view.View.class);
    }

    public static Matcher<View> matcherForAtIndex(final int index, final Matcher<View> innerMatcher) {
        return new BaseMatcher<View>() {
            int count = 0;

            @Override
            public boolean matches(Object item) {
                if (!innerMatcher.matches(item)) return false;

                if (count == index) {
                    count = 0;
                    return true;
                }
                ++count;
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("matches " + index + "th view.");
            }
        };
    }

    public static Matcher<View> matcherForAnything() {
        return isAssignableFrom(View.class);
    }

}
