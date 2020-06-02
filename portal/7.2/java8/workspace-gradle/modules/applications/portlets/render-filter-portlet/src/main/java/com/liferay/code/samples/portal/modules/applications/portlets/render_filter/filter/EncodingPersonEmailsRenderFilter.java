package com.liferay.code.samples.portal.modules.applications.portlets.render_filter.filter;

import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.model.Person;
import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.portlet.MembersListPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * RenderFilter that checks if a list of <code>Person</code> is present to be rendered and, in that case,
 * it ofuscates the emails before the portlet is rendered.
 *
 * This filter shows how RenderFilters can be used to alter the request/response data before the portlet can render it.
 *
 * A unique instance of this filter will be created and added to the FilterChain associated to the filter specified in
 * property <code>javax.portlet.name</code> in the <code>@Component</code> configuration.
 *
 * The portlet's filterChain is created keeping the filters ordered by the <code>service.ranking:Integer</code> value
 * configured in the <code>property</code> field of <code>@Component</code> annotation.
 *
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + MembersListPortlet.MEMBERSLIST_PORTLET_NAME, //Links Filter to  portlet by name
                "service.ranking:Integer=1"  //Specifies filter order in FilterChain. Highest number means higher preference
        },
        service = PortletFilter.class
)
public class EncodingPersonEmailsRenderFilter implements RenderFilter {

    /**
     * Implementation of filter method. This is where the filter can intercept the Request or adapt data both in the
     * request and in the response.
     *
     * Your code need to invoke the chain.doFilter() method to continue request processing (either by invoking the next
     * fitler in the chain or, if this fitler is the last one in the chain, invoking to the target phase in the portlet).
     *
     * You can also stop (intercept) the processing of the request by throwing a PortletException
     */
    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
            throws IOException, PortletException {

        //This is executed before the portlet render
        Optional.ofNullable((List<Person>)request.getAttribute(MembersListPortlet.MEMBERLIST_ATTRIBUTE))
                .ifPresent(personList ->
                        request.setAttribute(MembersListPortlet.MEMBERLIST_ATTRIBUTE, ofuscateEmails(personList)));

        // Invoke the rest of the filters in the chain
        //  (it also invokes the Portlet render method if this is the last filter in the chain
        chain.doFilter(request, response);

    }

    private List<Person> ofuscateEmails(List<Person> list) {
        return list.stream()
                .map(this::ofuscatePersonEmail)
                .collect(Collectors.toList());
    }

    /**
     *  Replaces the last three characters before and after the '@' in emails with dots
     *  For example an email like foobar@foobar.com will be changed to foo...@....bar.com
     *
     * @param person The Person which email should be ofuscated
     * @return a new <code>Person</code> (<code>Person</code> is inmutable) with the ofuscated email.
     */
    private Person ofuscatePersonEmail(Person person) {
        return new Person(person.getName(),
                          person.getEmail().replaceFirst("(.+)(...)@(...)(.*)", "$1...@...$4"));

    }

    /**
     * Method executed when the filter is instantiated and added to the Filter chain. The FilterConfig contain any init
     * param or initial configuration for the filter
     */
    @Override
    public void init(FilterConfig filterConfig) throws PortletException {
    }

    /**
     * Method invoked before the destruction of the filter and its removal from the FilterChain. Usually this method
     * includes any clean-up code required by the filter.
     */
    @Override
    public void destroy() {
    }
}
